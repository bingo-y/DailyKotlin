package com.bingo.dailykotlin.di.module

import android.app.Activity
import android.content.Context
import com.bingo.dailykotlin.di.scope.ContextLife
import com.bingo.dailykotlin.di.scope.PerActivity
import dagger.Module
import dagger.Provides

/**
 * @author bingo.
 * @date Create on 2017/11/23.
 * @Description
 */
@Module
class ActivityModule(val activity: Activity) {

    @Provides
    @PerActivity
    @ContextLife("Activity")
    fun provideActivity(): Activity = activity

    @Provides
    @PerActivity
    @ContextLife("Activity")
    fun provideActivityContext(): Context = activity

}