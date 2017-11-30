package com.bingo.dailykotlin.di.component

import android.app.Activity
import android.content.Context
import com.bingo.dailykotlin.di.module.FragmentModule
import com.bingo.dailykotlin.di.scope.ContextLife
import com.bingo.dailykotlin.di.scope.PerFragment
import com.bingo.dailykotlin.mvp.ui.fragment.NewsListFragment
import dagger.Component
import dagger.Module

/**
 * @author bingo.
 * @date Create on 2017/11/30.
 * @Description
 */
@PerFragment
@Component(modules = arrayOf(FragmentModule::class), dependencies = arrayOf(ApplicationComponent::class))
interface FragmentComponent {

    @ContextLife("Activity")
    fun getActivityContext(): Context

    @ContextLife("Application")
    fun getApplicationContext(): Context

    fun getActivity(): Activity

    fun inject(newsListFragment: NewsListFragment)

}