package com.frame.admin.appframe.ui.login.di

import android.content.Context
import com.frame.admin.appframe.R
import com.frame.admin.appframe.common.LoadingProgressDialog
import com.frame.admin.appframe.di.scope.ActivityScope
import com.frame.admin.appframe.network.ApiService
import com.frame.admin.appframe.ui.login.LoginActivity
import com.frame.admin.appframe.ui.login.mvp.LoginContract
import com.frame.admin.appframe.ui.login.mvp.LoginModel
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class LoginModule {

    @ActivityScope
    @Binds
    abstract fun provideLoginView(activity: LoginActivity): LoginContract.View

    @Module
    companion object {

        @ActivityScope
        @Provides
        fun provideLoadingDialog(context: Context): LoadingProgressDialog {
            return LoadingProgressDialog.newInstance(context.getString(R.string.text_on_login))
        }

        @ActivityScope
        @Provides
        fun provideLoginModel(apiService: ApiService): LoginModel {
            return LoginModel(apiService)
        }
    }
}