package com.frame.admin.appframe.network

import com.frame.admin.appframe.BaseApplication
import com.frame.admin.appframe.R
import com.frame.admin.appframe.data.BaseBean
import io.reactivex.disposables.CompositeDisposable

/**
 * @author :lazyMing
 * email   :407555147@qq.com
 * date    :2018/12/1
 * desc    :abstractObserver 实现类 (再次封装)
 * address :
 * update  :
 */
abstract class ObserverImpl<T : BaseBean<*>>(private val disposable: CompositeDisposable? = null) : AbstractObserver<T>() {

    override fun onNext(t: T) {
        if (t.errorCode == -1001) {
            super.onError(ApiException(ApiException.ERROR_TOKEN_EXPIRED,
                    t.errorMsg,
                    BaseApplication.INSTANCE.applicationContext.getString(R.string.text_token_expired)))
            return
        }
        onSuccess(t)
    }
}