package com.bingo.dailykotlin.di.component

import android.content.Context
import com.bingo.dailykotlin.di.module.ApplicationModule
import com.bingo.dailykotlin.di.scope.ContextLife
import com.bingo.dailykotlin.di.scope.PerApp
import dagger.Component

/**
 * @author bingo.
 * @date Create on 2017/11/23.
 * @Description
 */
@PerApp
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    @ContextLife("Application")
    fun getApplication(): Context

}