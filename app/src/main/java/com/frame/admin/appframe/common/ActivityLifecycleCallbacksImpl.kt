package com.frame.admin.appframe.common

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.frame.admin.appframe.common.inter.IAppCompatActivity
import com.frame.admin.appframe.common.language.LanguageManager
import me.jessyan.autosize.AutoSize
import javax.inject.Inject

/**
 * @author :lazyMing
 * email   :407555147@qq.com
 * date    :2018/11/30
 * desc    :activity 生命周期回调实现类
 * address :
 * update  :
 */
class ActivityLifecycleCallbacksImpl @Inject constructor() : Application.ActivityLifecycleCallbacks {

    @Inject
    lateinit var mActivityList: MutableList<Activity?>

    @Inject
    lateinit var mLanguageManage: LanguageManager

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        if (!mActivityList.contains(activity)) {
            mActivityList.add(activity)
        }

        // 设置语言
        mLanguageManage.updateLanguageConfiguration(activity!!)

        // 由于在设置语言中使用了displayMetrics属性，导致框架里面的屏幕适配失效，需要重新设定activity的density
        AutoSize.autoConvertDensityOfGlobal(activity)

        // 处理activity事件
        if (activity is IAppCompatActivity) {
            activity.beforeInflateView()
            activity.setContentView(activity.getLayoutResId())
            activity.initEvent()
        }
    }

    override fun onActivityDestroyed(activity: Activity?) {
        if (mActivityList.contains(activity)) {
            mActivityList.remove(activity)
        }
    }

    override fun onActivityPaused(activity: Activity?) {

    }

    override fun onActivityResumed(activity: Activity?) {

    }

    override fun onActivityStarted(activity: Activity?) {

    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {

    }

    override fun onActivityStopped(activity: Activity?) {

    }
}