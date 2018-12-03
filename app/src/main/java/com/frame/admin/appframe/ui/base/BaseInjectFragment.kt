package com.frame.admin.appframe.ui.base

import com.frame.admin.appframe.mvp.IPresenter
import javax.inject.Inject

abstract class BaseInjectFragment<P : IPresenter<*>> : BaseFragment() {

    @Inject
    lateinit var mPresenter: P
}