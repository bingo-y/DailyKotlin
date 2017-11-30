package com.bingo.dailykotlin.mvp.view

import com.bingo.dailykotlin.mvp.entity.NewsChannel
import com.bingo.dailykotlin.mvp.view.base.IView

/**
 * @author bingo.
 * @date Create on 2017/11/28.
 * @Description
 */
interface INewsView: IView {

    fun initViewPager(newsChannels: List<NewsChannel>)

}