package com.frame.admin.appframe.ui.base

import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import com.frame.admin.appframe.BaseApplication
import com.frame.admin.appframe.mvp.IView
import dagger.android.DaggerFragment

abstract class BaseFragment : DaggerFragment(), IView {

    @LayoutRes
    abstract fun getLayoutResId(): Int

    abstract fun initEvent()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutResId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        initEvent()
    }

    open fun initView(view: View) {}

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMessage(message: String?) {
        Toast.makeText(BaseApplication.INSTANCE.applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun handleMessage(message: Message) {

    }
}