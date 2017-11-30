package com.bingo.dailykotlin.util

import com.socks.library.KLog
import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author bingo.
 * @date Create on 2017/11/23.
 * @Description
 */

fun Subscription.cancelSub() {
    if (!this.isUnsubscribed) {
        this.unsubscribe()
    }
}

fun <T> defaultSchedulers(): Observable.Transformer<T, T> {
    return Observable.Transformer { tObservable ->
                tObservable
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}

/**
 * from yyyy-MM-dd HH:mm:ss to MM-dd HH:mm
 */
fun formatDate(before: String): String {
    val after: String
    try {
        val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                .parse(before)
        after = SimpleDateFormat("MM-dd HH:mm", Locale.getDefault()).format(date)
    } catch (e: ParseException) {
        KLog.e("转换新闻日期格式异常：" + e.toString())
        return before
    }

    return after
}