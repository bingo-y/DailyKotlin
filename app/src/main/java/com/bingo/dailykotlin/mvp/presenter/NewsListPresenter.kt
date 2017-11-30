package com.bingo.dailykotlin.mvp.presenter

import com.bingo.dailykotlin.common.LoadNewsType
import com.bingo.dailykotlin.listener.RequestCallBack
import com.bingo.dailykotlin.mvp.entity.NewsSummary
import com.bingo.dailykotlin.mvp.interator.INewsListInteractor
import com.bingo.dailykotlin.mvp.interator.NewsListInteractor
import com.bingo.dailykotlin.mvp.presenter.base.BasePresenter
import com.bingo.dailykotlin.mvp.view.INewsListView
import com.bingo.dailykotlin.mvp.view.base.IView
import javax.inject.Inject

/**
 * @author bingo.
 * @date Create on 2017/11/30.
 * @Description
 */
class NewsListPresenter @Inject constructor(newsListInteractor: NewsListInteractor)
    : BasePresenter<INewsListView, List<NewsSummary>>(), INewsListPresenter, RequestCallBack<List<NewsSummary>> {

    private var mNewsListInteractor: INewsListInteractor<List<NewsSummary>>? = newsListInteractor

    private var mNewsType: String? = null
    private var mNewsId: String? = null
    private var mStartPage: Int = 0

    private var misFirstLoad: Boolean = false
    private var mIsRefresh = true

    override fun setNewsTypeAndId(newsType: String?, newsId: String?) {
        mNewsType = newsType
        mNewsId = newsId
    }

    override fun refreshData() {
        mStartPage = 0
        mIsRefresh = true
        loadNewsData()
    }

    override fun loadMore() {
        mIsRefresh = false
        loadNewsData()
    }

    override fun beforeRequest() {
        if (!misFirstLoad) {
            mView?.showProgress()
        }
    }

    override fun onSuccess(data: List<NewsSummary>) {
        misFirstLoad = true
        mStartPage += 20

        val loadType = if (mIsRefresh) LoadNewsType.TYPE_REFRESH_SUCCESS else LoadNewsType.TYPE_LOAD_MORE_SUCCESS
        mView?.apply {
            mView?.setNewsList(data, loadType)
            mView?.hideProgress()
        }
    }

    override fun onCreate() {
        mView?.let {
            loadNewsData()
        }
    }

    override fun onError(message: String?) {
        super.onError(message)
        mView?.let {
            val loadType = if (mIsRefresh) LoadNewsType.TYPE_REFRESH_ERROR else LoadNewsType.TYPE_LOAD_MORE_ERROR
            mView?.setNewsList(null, loadType)
        }
    }

    override fun attachView(view: IView?) {
        super.attachView(view)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun loadNewsData() {
        mSubscription = mNewsListInteractor?.loadNews(this, mNewsType, mNewsId, mStartPage)
    }


}