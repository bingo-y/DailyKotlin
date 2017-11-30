package com.bingo.dailykotlin.mvp.presenter

import com.bingo.dailykotlin.mvp.entity.NewsChannel
import com.bingo.dailykotlin.mvp.interator.INewsInterator
import com.bingo.dailykotlin.mvp.interator.NewsInterator
import com.bingo.dailykotlin.mvp.presenter.base.BasePresenter
import com.bingo.dailykotlin.mvp.view.INewsView
import com.bingo.dailykotlin.mvp.view.base.IView
import javax.inject.Inject

/**
 * @author bingo.
 * @date Create on 2017/11/28.
 * @Description
 */
class NewsPresenter @Inject constructor(newsInterator: NewsInterator): BasePresenter<INewsView, List<NewsChannel>>(), INewsPresenter {

    var mNewsInteractor: INewsInterator<List<NewsChannel>>? = null

    init {
        mNewsInteractor =  newsInterator
    }

    fun loadChannel() {
        mSubscription = mNewsInteractor?.loadNewsChannels(this)
    }


    override fun onChannelChanged() {
        loadChannel()
    }

    override fun onSuccess(data: List<NewsChannel>) {
        super.onSuccess(data)
        mView?.initViewPager(data)
    }

    override fun onCreate() {
        super.onCreate()
        loadChannel()
    }

    override fun onError(message: String?) {
        super.onError(message)
    }

    override fun attachView(view: IView?) {
        super.attachView(view)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}