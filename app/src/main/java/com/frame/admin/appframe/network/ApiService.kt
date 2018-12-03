package com.frame.admin.appframe.network

import com.frame.admin.appframe.data.BaseBean
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * @author :lazyMing
 * email   :407555147@qq.com
 * date    :2018/11/30
 * desc    :api 接口
 * address :
 * update  :
 */
interface ApiService {

    // 登录
    @FormUrlEncoded
    @POST("user/login")
    fun login(@Field("username") userName: String, @Field("password") password: String)
            : Observable<BaseBean.LoginBean>
}