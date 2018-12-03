package com.frame.admin.appframe.mvp

import io.reactivex.disposables.CompositeDisposable

/**
 * @author :lazyMing
 * email   :407555147@qq.com
 * date    :2018/12/1
 * desc    :泛型IPresenter接口实现类
 * address :
 * update  :
 */
open class BasePresenter<V : IView, M : IModel> constructor(private var mModel: M? = null,
                                                            private var mCompositeDisposable: CompositeDisposable?)
    : IPresenter<V> {

    lateinit var mView: V

    override fun onAttach(view: V) {
        checkNotNull(view) { "${view::class.java.simpleName} cannot be null." }
        this.mView = view
    }

    override fun onDestroy() {
        mModel?.onDestroy()
        mModel = null
        mCompositeDisposable?.clear()
        mCompositeDisposable = null
    }
}