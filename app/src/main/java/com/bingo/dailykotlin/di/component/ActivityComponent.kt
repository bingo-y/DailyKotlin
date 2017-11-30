package com.bingo.dailykotlin.di.component

import android.app.Activity
import android.app.Application
import android.content.Context
import com.bingo.dailykotlin.di.module.ActivityModule
import com.bingo.dailykotlin.di.scope.ContextLife
import com.bingo.dailykotlin.di.scope.PerActivity
import com.bingo.dailykotlin.mvp.ui.MainActivity
import dagger.Component

/**
 * @author bingo.
 * @date Create on 2017/11/24.
 * @Description
 */
@PerActivity
@Component(modules = arrayOf(ActivityModule::class), dependencies = arrayOf(ApplicationComponent::class))
interface ActivityComponent {

    @ContextLife("Activity")
    fun getActivityContext(): Context

    @ContextLife("Activity")
    fun getActivity(): Activity

    @ContextLife("Application")
    fun getApplication(): Context

    fun inject(activity: MainActivity)

}