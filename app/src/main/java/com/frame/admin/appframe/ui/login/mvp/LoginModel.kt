package com.frame.admin.appframe.ui.login.mvp

import com.frame.admin.appframe.data.BaseBean
import com.frame.admin.appframe.network.ApiService
import io.reactivex.Observable
import javax.inject.Inject

class LoginModel @Inject constructor(private val apiService: ApiService) :
        LoginContract.Model {

    override fun login(userName: String, password: String): Observable<BaseBean.LoginBean> {
        return apiService.login(userName, password)
    }

    override fun onDestroy() {

    }
}