package com.bingo.dailykotlin.mvp.interator

import com.bingo.dailykotlin.listener.RequestCallBack
import rx.Subscription

/**
 * @author bingo.
 * @date Create on 2017/11/27.
 * @Description
 */
interface INewsInterator<T> {

    fun loadNewsChannels(callback: RequestCallBack<T>): Subscription

}