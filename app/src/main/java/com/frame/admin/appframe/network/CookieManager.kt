package com.frame.admin.appframe.network

import android.content.Context
import com.frame.admin.appframe.util.cookie.PersistentCookieJar
import com.frame.admin.appframe.util.cookie.cache.SetCookieCache
import com.frame.admin.appframe.util.cookie.persistence.SharedPrefsCookiePersistor

class CookieManager(context: Context) {

    private var cookieJar: PersistentCookieJar = PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(context))

    fun getCookieJar(): PersistentCookieJar {
        return cookieJar
    }

    fun clearCookieInfo() {
        cookieJar.clear()
    }

    companion object {

        private var INSTANCE: CookieManager? = null

        fun getInstance(context: Context): CookieManager {
            return INSTANCE ?: CookieManager(context).apply { INSTANCE = this }
        }
    }

}