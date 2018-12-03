@file:Suppress("DEPRECATION")

package com.frame.admin.appframe.common.language

import android.content.Context
import android.content.SharedPreferences
import com.frame.admin.appframe.constant.Constants.LANGUAGE_TYPE
import com.frame.admin.appframe.constant.Constants.LANGUAGE_TYPE_ENGLISH
import com.frame.admin.appframe.constant.Constants.LANGUAGE_TYPE_SIMPLIFIED_CHINESE
import com.frame.admin.appframe.constant.Constants.LANGUAGE_TYPE_TRADITIONAL_CHINESE
import com.frame.admin.appframe.util.PreferenceHelper.get
import com.frame.admin.appframe.util.PreferenceHelper.set
import java.util.*
import javax.inject.Inject

/**
 * @author :lazyMing
 * email   :407555147@qq.com
 * date    :2018/11/30
 * desc    :语言管理
 * address :
 * update  :
 */
class LanguageManager @Inject constructor(private val preferences: SharedPreferences) {

    fun changeLanguage(context: Context, languageType: Int?) {
        val configuration = context.resources.configuration
        val locale = when (languageType) {
            LANGUAGE_TYPE_ENGLISH -> Locale.ENGLISH
            LANGUAGE_TYPE_SIMPLIFIED_CHINESE -> Locale.SIMPLIFIED_CHINESE
            LANGUAGE_TYPE_TRADITIONAL_CHINESE -> Locale.TRADITIONAL_CHINESE
            else -> Locale.getDefault()
        }

        configuration.locale = locale
        context.resources.updateConfiguration(configuration, context.resources.displayMetrics)
        preferences[LANGUAGE_TYPE] = languageType
    }

    fun updateLanguageConfiguration(context: Context) {
        val i = preferences[LANGUAGE_TYPE, 0]
        changeLanguage(context, i)
    }
}