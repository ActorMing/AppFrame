package com.frame.admin.appframe.data

/**
 * @author :lazyMing
 * email   :407555147@qq.com
 * date    :2018/12/1
 * desc    :baseBean
 * address :
 * update  :
 */
abstract class BaseBean<T : Any?> {
    val errorCode: Int = 0
    val errorMsg: String? = null
    abstract var data: T?

    /********************* Begin: Login Data ***********************/
    data class LoginBean(override var data: LoginData?) : BaseBean<LoginData?>()

    data class LoginData(val id: Int,
                         val username: String)
    /********************* End: Login Data *************************/
}