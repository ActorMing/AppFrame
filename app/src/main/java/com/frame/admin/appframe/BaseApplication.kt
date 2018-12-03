package com.frame.admin.appframe

import android.app.Activity
import com.frame.admin.appframe.common.ActivityLifecycleCallbacksImpl
import com.frame.admin.appframe.common.language.LanguageManager
import com.frame.admin.appframe.di.component.DaggerApplicationComponent
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import me.jessyan.autosize.AutoSizeConfig
import me.jessyan.autosize.unit.Subunits
import javax.inject.Inject

/**
 * @author :lazyMing
 * email   :407555147@qq.com
 * date    :2018/11/30
 * desc    :baseApplication
 * address :
 * update  :
 */
class BaseApplication : DaggerApplication() {

    @Inject
    lateinit var mActivityLifecycleCallbacksImpl: ActivityLifecycleCallbacksImpl

    @Inject
    lateinit var mActivityList: MutableList<Activity?>

    @Inject
    lateinit var mLanguageManager: LanguageManager

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder().application(application = this).build()
    }

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        Logger.addLogAdapter(createLogAdapter())
        initAutoSize()
        INSTANCE = this
        mLanguageManager.updateLanguageConfiguration(applicationContext)
        registerActivityLifecycleCallbacks(mActivityLifecycleCallbacksImpl)
    }

    private fun createLogAdapter(): AndroidLogAdapter {
        return object : AndroidLogAdapter() {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        }
    }

    private fun initAutoSize() {
        AutoSizeConfig.getInstance()
                .unitsManager
                .setSupportDP(false)
                .setSupportSP(false)
                .setSupportSubunits(Subunits.PT)
    }

    /**
     * 退出app
     */
    fun quitApp() {
        for (activity in mActivityList) {
            activity?.finish()
        }
    }

    companion object {
        lateinit var INSTANCE: BaseApplication
            private set
    }
}