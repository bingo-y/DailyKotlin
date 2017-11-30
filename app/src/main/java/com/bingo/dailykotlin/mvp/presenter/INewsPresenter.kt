package com.bingo.dailykotlin.mvp.presenter

import com.bingo.dailykotlin.mvp.presenter.base.IPresenter

/**
 * @author bingo.
 * @date Create on 2017/11/28.
 * @Description
 */
interface INewsPresenter: IPresenter {

    fun onChannelChanged()

}