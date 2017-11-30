package com.bingo.dailykotlin.mvp.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.bingo.dailykotlin.App
import com.bingo.dailykotlin.R
import com.bingo.dailykotlin.di.component.ActivityComponent
import com.bingo.dailykotlin.di.component.DaggerActivityComponent
import com.bingo.dailykotlin.di.module.ActivityModule
import com.bingo.dailykotlin.mvp.presenter.base.IPresenter
import com.bingo.dailykotlin.util.cancelSub
import com.socks.library.KLog
import rx.Subscription

/**
 * @author bingo.
 * @date Create on 2017/11/23.
 * @Description
 */
abstract class BaseActivity<T: IPresenter> : AppCompatActivity() {

    protected val mActivityComponent: ActivityComponent by lazy {
        DaggerActivityComponent.builder()
                .applicationComponent((application as App).getApplicationComponent())
                .activityModule(ActivityModule(this))
                .build()
    }

    protected var mPresenter: T? = null

    abstract fun getLayoutId(): Int

    abstract fun initInjector(): Unit

    abstract fun initViews()

    protected var mSubscription: Subscription? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        KLog.i(javaClass.simpleName, "onCreate")

        val layoutId = getLayoutId()
        setContentView(layoutId)
        initInjector()
        initToolBar()
        initViews()
        if (mPresenter != null) {
            mPresenter!!.onCreate()
        }

    }


    private fun initToolBar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    override fun onDestroy() {
        super.onDestroy()

        mPresenter?.onDestroy()
        mSubscription?.cancelSub()
    }

}