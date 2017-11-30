package com.bingo.dailykotlin.mvp.presenter.base

import com.bingo.dailykotlin.mvp.view.base.IView

/**
 * @author bingo.
 * @date Create on 2017/11/23.
 * @Description
 */
interface IPresenter {

    fun onCreate()

    fun attachView(view: IView?)

    fun onDestroy()

}