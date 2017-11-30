package com.bingo.dailykotlin.mvp.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.bingo.dailykotlin.App
import com.bingo.dailykotlin.di.component.DaggerFragmentComponent
import com.bingo.dailykotlin.di.component.FragmentComponent
import com.bingo.dailykotlin.di.module.FragmentModule
import com.bingo.dailykotlin.mvp.presenter.base.IPresenter
import com.bingo.dailykotlin.util.cancelSub
import rx.Subscription

/**
 * @author bingo.
 * @date Create on 2017/11/28.
 * @Description
 */
abstract class BaseFragment<T: IPresenter>: Fragment() {


    protected val mFragmentComponent: FragmentComponent by lazy {
        DaggerFragmentComponent.builder()
                .applicationComponent((activity?.application as App).getApplicationComponent())
                .fragmentModule(FragmentModule(this))
                .build()
    }
    protected var mPresenter: T? = null

    private var mFragmentView: View? = null

    abstract fun initInjector()

    abstract fun initViews(view: View)

    abstract fun getLayoutId(): Int

    protected var mSubscription: Subscription? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initInjector()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mFragmentView == null) {
            mFragmentView = inflater.inflate(getLayoutId(), container, false)
        }
        return mFragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(mFragmentView!!)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mPresenter != null) {
            mPresenter!!.onDestroy()
        }
        mPresenter?.onDestroy()
        mSubscription?.cancelSub()
    }


}