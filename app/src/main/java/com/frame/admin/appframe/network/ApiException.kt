package com.frame.admin.appframe.network

import com.frame.admin.appframe.BaseApplication
import com.frame.admin.appframe.R
import com.frame.admin.appframe.util.NetworkUtil
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

/**
 * @author :lazyMing
 * email   :407555147@qq.com
 * date    :2018/12/1
 * desc    :接口的异常处理
 * address :
 * update  :
 */
class ApiException(val errorCode: Int, override val message: String?, val showMessage: String) : Exception() {

    companion object {
        const val ERROR_UNKNOWN = -1
        const val ERROR_UNKNOWN_HOST = -2
        const val ERROR_CONNECT_TIMEOUT = -3
        const val ERROR_TOKEN_EXPIRED = -4

        fun parseException(error: Throwable): ApiException {
            if (error is ApiException) return error

            val context = BaseApplication.INSTANCE.applicationContext
            val showMessage: String
            var code = 0
            if (error is UnknownHostException || error is HttpException) {
                code == ERROR_UNKNOWN_HOST
                showMessage = if (!NetworkUtil.isNetworkAvaiable(context)) {
                    context.getString(R.string.text_network_not_available)
                } else {
                    context.getString(R.string.text_address_not_reachable)
                }
            } else if (error is ConnectException || error is TimeoutException
                    || error is SocketException || error is SocketTimeoutException) {
                code = ERROR_CONNECT_TIMEOUT
                showMessage = context.getString(R.string.text_network_timeout)
            } else {
                code = ERROR_UNKNOWN
                showMessage = error.message ?: context.getString(R.string.text_unknown_error)
            }
            return ApiException(code, error.message, showMessage)
        }
    }
}