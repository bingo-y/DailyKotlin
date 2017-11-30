package com.bingo.dailykotlin.listener

/**
 * @author bingo.
 * @date Create on 2017/11/23.
 * @Description
 */
interface RequestCallBack<T> {
    fun beforeRequest()

    fun onSuccess(data : T)

    fun onError(message : String?)
}