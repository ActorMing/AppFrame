package com.frame.admin.appframe.network

import com.frame.admin.appframe.constant.Constants
import com.frame.admin.appframe.util.cookie.PersistentCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.X509TrustManager

object OkHttpClientFactory {

    fun createUnsafeOkHttpClient(interceptor: HttpLoggingInterceptor, cookieJar: PersistentCookieJar): OkHttpClient {
        val builder = OkHttpClient.Builder()
                .connectTimeout(Constants.DEFAULT_TIME_OUT, TimeUnit.MILLISECONDS)
                .readTimeout(Constants.DEFAULT_TIME_OUT, TimeUnit.MILLISECONDS)
                .writeTimeout(Constants.DEFAULT_TIME_OUT, TimeUnit.MILLISECONDS)
                .addInterceptor(interceptor)
                .cookieJar(cookieJar)
                .followRedirects(true)
                .retryOnConnectionFailure(true)
                .followSslRedirects(true)
        try {
            val x509TrustManager = object : X509TrustManager {
                override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {
                }

                override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }

            }
            val trustManagerArray = arrayOf(x509TrustManager)
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustManagerArray, SecureRandom())
            return builder.sslSocketFactory(sslContext.socketFactory, trustManagerArray[0])
                    .hostnameVerifier { _, _ -> true }
                    .build()
        } catch (e: Exception) {
            e.printStackTrace()
            return builder.build()
        }
    }

}