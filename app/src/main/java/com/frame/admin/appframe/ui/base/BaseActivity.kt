package com.frame.admin.appframe.ui.base

import android.os.Message
import com.frame.admin.appframe.common.inter.IAppCompatActivity
import com.frame.admin.appframe.mvp.IPresenter
import com.frame.admin.appframe.mvp.IView
import com.frame.admin.appframe.util.SnackbarUtil
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * @author :lazyMing
 * email   :407555147@qq.com
 * date    :2018/12/1
 * desc    :baseActivity
 * address :
 * update  :
 */
abstract class BaseActivity<P : IPresenter<*>> : DaggerAppCompatActivity(), IAppCompatActivity, IView {

    @Inject
    lateinit var mPresenter: P

    override fun beforeInflateView() {

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMessage(message: String?) {
        SnackbarUtil.showNormalToast(this, message!!)
    }

    override fun handleMessage(message: Message) {

    }
}