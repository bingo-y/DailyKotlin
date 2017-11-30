package com.bingo.dailykotlin.mvp.interator

import com.bingo.dailykotlin.common.ApiConstants
import com.bingo.dailykotlin.common.HostType
import com.bingo.dailykotlin.listener.RequestCallBack
import com.bingo.dailykotlin.mvp.entity.NewsSummary
import com.bingo.dailykotlin.network.RetrofitManager
import com.bingo.dailykotlin.util.defaultSchedulers
import com.bingo.dailykotlin.util.formatDate
import com.socks.library.KLog
import rx.Observable
import rx.Subscriber
import rx.Subscription
import rx.functions.Func1
import rx.functions.Func2
import javax.inject.Inject

/**
 * @author bingo.
 * @date Create on 2017/11/29.
 * @Description
 */
class NewsListInteractor @Inject constructor(): INewsListInteractor<List<NewsSummary>> {


    override fun loadNews(callBack: RequestCallBack<List<NewsSummary>>, type: String?, id: String?, startPage: Int): Subscription {
        return RetrofitManager.getInstance(HostType.NETEASE_NEWS_VIDEO)
                .getNewsListObservable(type, id, startPage)
                .flatMap { map ->
                    if (id?.endsWith(ApiConstants.HOUSE_ID) == true) {
                        // 房产实际上针对地区的它的id与返回key不同
                        Observable.from(map.get("北京"))
                    } else Observable.from(map.get(id))
                }.map { newsSummary ->
                    val ptime = formatDate(newsSummary.ptime ?: "")
                    newsSummary.ptime = ptime
                    newsSummary
                }.distinct()
                .toSortedList{t1: NewsSummary?, t2: NewsSummary? -> t1?.ptime?.compareTo(t2?.ptime ?: "") }
                .compose(defaultSchedulers())
                .subscribe(object : Subscriber<List<NewsSummary>>() {
                    override fun onCompleted() {
                        KLog.d()
                        //                        checkNetState(listener);
                    }

                    override fun onError(e: Throwable) {
                        try {
                            KLog.e(e.toString())
                            //                        checkNetState(listener);
                            //                        if (!NetUtil.isNetworkAvailable(App.getAppContext())) {
                            callBack.onError(e.message)
                        } catch (e: Throwable) {

                        }

                        //                        }
                    }

                    override fun onNext(newsSummaries: List<NewsSummary>) {
                        KLog.d()
                        callBack.onSuccess(newsSummaries)
                    }
                })
    }

}