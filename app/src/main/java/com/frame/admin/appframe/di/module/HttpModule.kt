package com.frame.admin.appframe.di.module

import android.content.Context
import com.frame.admin.appframe.constant.Constants
import com.frame.admin.appframe.network.ApiService
import com.frame.admin.appframe.network.CookieManager
import com.frame.admin.appframe.network.OkHttpClientFactory
import com.frame.admin.appframe.util.cookie.PersistentCookieJar
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@dagger.Module
class HttpModule {

    @Singleton
    @Provides
    fun provideAipService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().client(client)
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor, cookieJar: PersistentCookieJar): OkHttpClient {
        return OkHttpClientFactory.createUnsafeOkHttpClient(interceptor, cookieJar)
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun provideCookieJar(context: Context): PersistentCookieJar {
        return CookieManager.getInstance(context).getCookieJar()
    }

}