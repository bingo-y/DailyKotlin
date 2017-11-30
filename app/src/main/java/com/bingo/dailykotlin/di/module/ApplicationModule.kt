package com.bingo.dailykotlin.di.module

import android.content.Context
import com.bingo.dailykotlin.App
import com.bingo.dailykotlin.di.scope.ContextLife
import com.bingo.dailykotlin.di.scope.PerApp
import dagger.Module
import dagger.Provides

/**
 * @author bingo.
 * @date Create on 2017/11/23.
 * @Description
 */
@Module
class ApplicationModule(val application: App) {

    @Provides
    @PerApp
    @ContextLife("Application")
    fun provideApplicationContext(): Context = application.applicationContext

}