package com.bingo.dailykotlin.mvp.interator

import com.bingo.dailykotlin.listener.RequestCallBack
import rx.Subscription

/**
 * @author bingo.
 * @date Create on 2017/11/29.
 * @Description
 */
interface INewsListInteractor<T> {
    fun loadNews(callBack: RequestCallBack<T>, type: String?, id: String?, startPage: Int): Subscription
}