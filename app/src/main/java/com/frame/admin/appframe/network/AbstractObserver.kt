package com.frame.admin.appframe.network

import android.widget.Toast
import com.frame.admin.appframe.BaseApplication
import com.orhanobut.logger.Logger
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class AbstractObserver<T : Any>(private val disposable: CompositeDisposable? = null) : Observer<T> {

    abstract fun onSuccess(bean: T)

    abstract fun onFail(error: ApiException)

    override fun onSubscribe(d: Disposable) {
        disposable?.add(d)
    }

    override fun onNext(t: T) {
        onSuccess(t)
    }

    override fun onError(e: Throwable) {
        Logger.e("onError", e.message ?: "onError")
        val apiException = ApiException.parseException(e)
        if (apiException.errorCode == ApiException.ERROR_TOKEN_EXPIRED) {
            quitAndStartLogin(apiException.showMessage)
            return
        }
        onFail(apiException)
    }

    override fun onComplete() {
        Logger.e("onComplete")
    }

    private fun quitAndStartLogin(showMessage: String) {
        BaseApplication.INSTANCE.quitApp()
        val context = BaseApplication.INSTANCE.applicationContext
        Toast.makeText(context, showMessage, Toast.LENGTH_SHORT).show()
        CookieManager.getInstance(context).clearCookieInfo()
//        val intent = Intent(context, LoginActivity::class.java)
//        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//        context.startActivity(intent)
    }

}
