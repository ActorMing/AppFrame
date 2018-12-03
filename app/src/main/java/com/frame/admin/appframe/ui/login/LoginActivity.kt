package com.frame.admin.appframe.ui.login

import android.widget.TextView
import com.frame.admin.appframe.R
import com.frame.admin.appframe.common.LoadingProgressDialog
import com.frame.admin.appframe.common.inter.IAppCompatActivity
import com.frame.admin.appframe.ui.base.BaseActivity
import com.frame.admin.appframe.ui.login.mvp.LoginContract
import com.frame.admin.appframe.ui.login.mvp.LoginPresenter
import com.frame.admin.appframe.util.SnackbarUtil
import com.frame.admin.appframe.util.ext.registerClickEvent
import com.frame.admin.appframe.util.ext.registerTextChangeEvent
import com.frame.admin.appframe.util.ext.setupUi
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

/**
 * @author :lazyMing
 * email   :407555147@qq.com
 * date    :2018/12/1
 * desc    :loginActivity
 * address :
 * update  :
 */
class LoginActivity : BaseActivity<LoginPresenter>(), LoginContract.View, IAppCompatActivity {

    @Inject
    lateinit var mProgressDialog: LoadingProgressDialog

    override fun getLayoutResId(): Int {
        return R.layout.activity_login
    }

    override fun initEvent() {
        setupUi(root)
        mPresenter.onAttach(this)
    }

    override fun getTitleText(): TextView {
        return loginTv
    }

    override fun closeDisposable(): Disposable {
        return registerClickEvent(closeIv) { finish() }
    }

    override fun forgetPasswordDisposable(): Disposable {
        return registerClickEvent(forgetPasswordTv) { showMessage(getString(R.string.text_developing)) }
    }

    override fun registerDisposable(): Disposable {
//        return registerClickEvent(registerTipsTv) { startActivity(Intent(this, RegisterActivity::class.java)) }
        return registerClickEvent(registerTipsTv) {}
    }

    override fun userNameDisposable(): Disposable {
        return registerTextChangeEvent(userNameEdit, userNameInputLayout)
    }

    override fun passwordDisposable(): Disposable {
        return registerTextChangeEvent(passwordEdit, passwordInputLayout)
    }

    override fun commitObservable(): Observable<Any> {
        return RxView.clicks(loginBtn)
    }

    override fun inflateUserName(userName: String) {
        userNameEdit.setText(userName)
    }

    override fun userNameText(): String {
        return userNameEdit.text.toString().trim()
    }

    override fun passwordText(): String {
        return passwordEdit.text.toString().trim()
    }

    override fun showInputLayoutError(type: Int, errorMsg: String) {
        when (type) {
            0 -> {
                userNameInputLayout.isErrorEnabled = true
                userNameInputLayout.error = errorMsg
            }
            1, 2 -> {
                passwordInputLayout.isErrorEnabled = true
                passwordInputLayout.error = errorMsg
            }
        }
    }

    override fun showProcessDialog() {
        mProgressDialog.show(supportFragmentManager, "")
    }

    override fun hideProcessDialog() {
        mProgressDialog.dismiss()
    }

    override fun startHomeActivity() {
        SnackbarUtil.showToastText(context = this, text = "mainActivity")
//        startActivity(Intent(this, MainActivity::class.java))
//        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroy()
    }
}