package com.frame.admin.appframe.mvp

import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
  * @author :lazyMing
  * email   :407555147@qq.com
  * date    :2018/11/30
  * desc    :baseView interface
  * address :
  * update  :
  */
interface IView {

    fun showLoading()

    fun hideLoading()

    fun showMessage(message: String?)

    fun handleMessage(message: Message)
}