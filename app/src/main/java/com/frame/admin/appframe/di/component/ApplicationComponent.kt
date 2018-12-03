package com.frame.admin.appframe.di.component

import android.app.Application
import com.frame.admin.appframe.BaseApplication
import com.frame.admin.appframe.di.module.ActivityBindingModule
import com.frame.admin.appframe.di.module.AppModule
import com.frame.admin.appframe.di.module.CommonModule
import com.frame.admin.appframe.di.module.HttpModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * @author :lazyMing
 * email   :407555147@qq.com
 * date    :2018/11/30
 * desc    :application 组件接口
 * address :
 * update  :
 */

@Singleton
@Component(modules = [AppModule::class, CommonModule::class, HttpModule::class,
    AndroidSupportInjectionModule::class, ActivityBindingModule::class])
interface ApplicationComponent : AndroidInjector<BaseApplication> {
    @dagger.Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}