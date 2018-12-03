package com.frame.admin.appframe.di.module

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.frame.admin.appframe.common.language.LanguageManager
import com.frame.admin.appframe.util.PreferenceHelper
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

/**
 * @author :lazyMing
 * email   :407555147@qq.com
 * date    :2018/11/30
 * desc    :公共的提供类
 * address :
 * update  :
 */
@Module
class CommonModule {

    @Provides
    fun providerCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Singleton
    @Provides
    fun providerActivityList(): List<Activity?> = mutableListOf()

    @Singleton
    @Provides
    fun providerSharedPreferences(context: Context) = PreferenceHelper.defaultPrefs(context)

    @Singleton
    @Provides
    fun providerLanguageManager(sharedPreferences: SharedPreferences) = LanguageManager(sharedPreferences)


}