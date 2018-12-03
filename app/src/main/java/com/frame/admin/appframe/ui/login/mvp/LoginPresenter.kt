package com.frame.admin.appframe.ui.login.mvp

import android.content.Context
import android.content.SharedPreferences
import com.frame.admin.appframe.R
import com.frame.admin.appframe.constant.Constants
import com.frame.admin.appframe.data.BaseBean
import com.frame.admin.appframe.mvp.BasePresenter
import com.frame.admin.appframe.network.ApiException
import com.frame.admin.appframe.network.ObserverImpl
import com.frame.admin.appframe.util.PreferenceHelper.set
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class LoginPresenter @Inject constructor(private val loginView: LoginContract.View,
                                         private val loginModel: LoginModel,
                                         private val disposable: CompositeDisposable) :
        BasePresenter<LoginContract.View, LoginModel>(loginModel, disposable) {

    @Inject
    lateinit var mContext: Context

    @Inject
    lateinit var mPreferences: SharedPreferences

    override fun onAttach(view: LoginContract.View) {
        mView = loginView
        registerEvents()
    }

    private fun registerEvents() {
        registerCommitEvent()
    }

    private fun registerCommitEvent() {
        mView.commitObservable()
                .filter {
                    return@filter validParams(mView.userNameText(), mView.passwordText())
                }
                .doOnNext { mView.showProcessDialog() }
                .debounce(800, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .switchMap {
                    return@switchMap loginModel.login(mView.userNameText(), mView.passwordText())
                }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { mView.hideProcessDialog() }
                .subscribe(object : ObserverImpl<BaseBean.LoginBean>(disposable) {
                    override fun onSuccess(bean: BaseBean.LoginBean) {
                        if (bean.errorCode == 0) {
                            mPreferences[Constants.LOGIN_USER_NAME] = bean.data?.username
                            mPreferences[Constants.LOGIN_LATEST_USER] = bean.data?.username
                            mView.startHomeActivity()
                        } else {
                            mView.showMessage(bean.errorMsg)
                            mView.hideProcessDialog()
                        }
                    }

                    override fun onFail(error: ApiException) {
                        mView.showMessage(error.showMessage)
                    }
                })
    }

    private fun validParams(userNameText: String, passwordText: String): Boolean {
        return when {
            userNameText.isEmpty() -> {
                mView.showInputLayoutError(0, mContext.getString(R.string.text_user_name_empty))
                return false
            }
            passwordText.isEmpty() -> {
                mView.showInputLayoutError(1, mContext.getString(R.string.text_password_empty))
                return false
            }
            passwordText.length < 6 -> {
                mView.showInputLayoutError(2, mContext.getString(R.string.text_password_too_short))
                return false
            }
            else -> true
        }
    }
}