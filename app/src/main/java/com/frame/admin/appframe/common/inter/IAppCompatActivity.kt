package com.frame.admin.appframe.common.inter

import androidx.annotation.LayoutRes

/**
 * @author :lazyMing
 * email   :407555147@qq.com
 * date    :2018/11/30
 * desc    :处理 activity fragment 获取layoutId和getIntent等
 * address :
 * update  :
 */
interface IAppCompatActivity {

    /**
     * before inflate view do something, like get intent data
     */
    fun beforeInflateView()

    /**
     * Get the content layout resource id.
     * @return layout resource id
     */
    @LayoutRes
    fun getLayoutResId(): Int

    /**
     * Start init something
     */
    fun initEvent()
}