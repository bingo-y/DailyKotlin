package com.bingo.dailykotlin.mvp.view

import com.bingo.dailykotlin.common.LoadNewsType
import com.bingo.dailykotlin.mvp.entity.NewsSummary
import com.bingo.dailykotlin.mvp.view.base.IView

/**
 * @author bingo.
 * @date Create on 2017/11/30.
 * @Description
 */
interface INewsListView : IView {

    fun setNewsList(newsSummary: List<NewsSummary>?, @LoadNewsType.checker loadType: Long)

}