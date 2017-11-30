package com.bingo.dailykotlin.mvp.view.base

/**
 * @author bingo.
 * @date Create on 2017/11/23.
 * @Description
 */
interface IView {

    fun showProgress()

    fun hideProgress()

    fun showMsg(message: String?)

}