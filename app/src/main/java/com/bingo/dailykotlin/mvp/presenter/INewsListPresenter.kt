package com.bingo.dailykotlin.mvp.presenter

import com.bingo.dailykotlin.mvp.presenter.base.IPresenter

/**
 * @author bingo.
 * @date Create on 2017/11/30.
 * @Description
 */
interface INewsListPresenter: IPresenter {

    fun setNewsTypeAndId(newsType: String?, newsId: String?)

    fun refreshData()

    fun loadMore()

}