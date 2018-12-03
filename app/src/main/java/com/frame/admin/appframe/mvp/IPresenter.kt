package com.frame.admin.appframe.mvp

/**
  * @author :lazyMing
  * email   :407555147@qq.com
  * date    :2018/12/1
  * desc    :泛型basePresenter接口
  * address :
  * update  :
  */
interface IPresenter<V: IView> {

    fun onAttach(view: V)

    fun onDestroy()
}