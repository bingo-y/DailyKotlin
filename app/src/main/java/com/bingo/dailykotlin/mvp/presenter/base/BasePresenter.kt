package com.bingo.dailykotlin.mvp.presenter.base

import com.bingo.dailykotlin.listener.RequestCallBack
import com.bingo.dailykotlin.mvp.view.base.IView
import com.bingo.dailykotlin.util.cancelSub
import rx.Subscription

/**
 * @author bingo.
 * @date Create on 2017/11/23.
 * @Description
 */
open class BasePresenter<T : IView, E> : IPresenter, RequestCallBack<E> {
    protected var mView: T? = null
    protected var mSubscription: Subscription? = null

    override fun beforeRequest() {
        mView?.showProgress()
    }

    override fun onSuccess(data: E) {
        mView?.hideProgress()
    }

    override fun onCreate() {

    }

    override fun onError(message: String?) {
        mView?.hideProgress()
        mView?.showMsg(message)
    }

    @Suppress("UNCHECKED_CAST")
    override fun attachView(view: IView?) {
        mView = view as? T
    }

    override fun onDestroy() {
        mSubscription?.cancelSub()
    }


}