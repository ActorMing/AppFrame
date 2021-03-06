package com.frame.admin.appframe.ui.login.mvp

import android.widget.TextView
import com.frame.admin.appframe.data.BaseBean
import com.frame.admin.appframe.mvp.IModel
import com.frame.admin.appframe.mvp.IView
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

interface LoginContract {

    interface View : IView {
        fun getTitleText(): TextView
        fun closeDisposable(): Disposable
        fun forgetPasswordDisposable(): Disposable
        fun registerDisposable(): Disposable
        fun userNameDisposable(): Disposable
        fun passwordDisposable(): Disposable
        fun commitObservable(): Observable<Any>
        fun inflateUserName(userName: String)
        fun userNameText(): String
        fun passwordText(): String
        /**
         * @param type 0 - name input layout type; 1 - password input layout type; 2 - password length type;
         * @param errorMsg error message tips.
         */
        fun showInputLayoutError(type: Int, errorMsg: String)

        fun showProcessDialog()
        fun hideProcessDialog()
        fun startHomeActivity()
    }

    interface Model : IModel {
        fun login(userName: String, password: String): Observable<BaseBean.LoginBean>
    }
}