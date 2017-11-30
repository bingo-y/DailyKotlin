package com.bingo.dailykotlin.common

import android.support.annotation.IntDef

/**
 * @author bingo.
 * @date Create on 2017/11/30.
 * @Description
 */
object LoadNewsType {
    const val TYPE_REFRESH_SUCCESS = 1L
    const val TYPE_REFRESH_ERROR = 2L
    const val TYPE_LOAD_MORE_SUCCESS = 3L
    const val TYPE_LOAD_MORE_ERROR = 4L

    @IntDef(TYPE_REFRESH_SUCCESS, TYPE_REFRESH_ERROR, TYPE_LOAD_MORE_SUCCESS, TYPE_LOAD_MORE_ERROR)
    @Retention(AnnotationRetention.SOURCE)
    annotation class checker
}