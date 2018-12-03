package com.frame.admin.appframe.di.module

import android.app.Application
import android.content.Context
import com.frame.admin.appframe.common.ActivityLifecycleCallbacksImpl
import dagger.Binds
import dagger.Module

/**
 * @author :lazyMing
 * email   :407555147@qq.com
 * date    :2018/11/30
 * desc    :为application类提供所需要的某些对象
 * address :
 * update  :
 */
@Module
abstract class AppModule {

    @Binds
    abstract fun providerContext(application: Application)
            : Context

    @Binds
    abstract fun providerActivityLifecycleCallbacks(callback: ActivityLifecycleCallbacksImpl)
            : Application.ActivityLifecycleCallbacks
}