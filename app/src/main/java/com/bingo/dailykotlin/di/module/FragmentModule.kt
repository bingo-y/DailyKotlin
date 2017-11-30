package com.bingo.dailykotlin.di.module

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import com.bingo.dailykotlin.di.scope.ContextLife
import com.bingo.dailykotlin.di.scope.PerFragment
import dagger.Module
import dagger.Provides

/**
 * @author bingo.
 * @date Create on 2017/11/30.
 * @Description
 */
@Module
class FragmentModule(val mFragment: Fragment) {

    @Provides
    @PerFragment
    @ContextLife("Activity")
    fun provideActivityContext(): Context {
        return mFragment.activity!!
    }

    @Provides
    @PerFragment
    fun provideActivity(): Activity {
        return mFragment.activity!!
    }

    @Provides
    @PerFragment
    fun provideFragment(): Fragment {
        return mFragment
    }
}