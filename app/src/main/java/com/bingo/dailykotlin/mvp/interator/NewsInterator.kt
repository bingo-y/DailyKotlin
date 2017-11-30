package com.bingo.dailykotlin.mvp.interator

import com.bingo.dailykotlin.App
import com.bingo.dailykotlin.R
import com.bingo.dailykotlin.common.ApiConstants
import com.bingo.dailykotlin.listener.RequestCallBack
import com.bingo.dailykotlin.mvp.entity.NewsChannel
import com.bingo.dailykotlin.util.defaultSchedulers
import rx.Observable
import rx.Subscriber
import rx.Subscription
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

/**
 * @author bingo.
 * @date Create on 2017/11/27.
 * @Description
 */
class NewsInterator @Inject constructor(): INewsInterator<List<NewsChannel>> {

    var newsChannels: MutableList<NewsChannel> = ArrayList()

    init {
        val channelName = App.appContext.resources.getStringArray(R.array.news_channel_name)
        val channerId = App.appContext.resources.getStringArray(R.array.news_channel_id)
        for (i in 0 until channelName.size) {
            newsChannels.add(NewsChannel(channelName[i].toString(), channerId[i].toString(),
                    ApiConstants.getType(channerId[i].toString()), i <= 5, i, i == 0))
        }

    }


    override fun loadNewsChannels(callback: RequestCallBack<List<NewsChannel>>): Subscription {
        return Observable.create(Observable.OnSubscribe<List<NewsChannel>> {
                    it.onNext(newsChannels)
                    it.onCompleted()
                }).compose(defaultSchedulers())
                .subscribe(object : Subscriber<List<NewsChannel>>() {
                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable) {
                        callback.onError(e.message)
                    }

                    override fun onNext(newsChannels: List<NewsChannel>) {
                        callback.onSuccess(newsChannels)
                    }
                })
    }

}