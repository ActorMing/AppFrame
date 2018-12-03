package com.frame.admin.appframe.di.module

import com.frame.admin.appframe.di.scope.ActivityScope
import com.frame.admin.appframe.ui.login.LoginActivity
import com.frame.admin.appframe.ui.login.di.LoginModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author :lazyMing
 * email   :407555147@qq.com
 * date    :2018/11/30
 * desc    :绑定界面的提供类
 * address :
 * update  :
 */
@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [LoginModule::class])
    abstract fun contributeLoginActivity(): LoginActivity
}