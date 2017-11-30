package com.bingo.dailykotlin

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import com.bingo.dailykotlin.di.component.ApplicationComponent
import com.bingo.dailykotlin.di.component.DaggerApplicationComponent
import com.bingo.dailykotlin.di.module.ApplicationModule
import com.socks.library.KLog

/**
 * @author bingo.
 * @date Create on 2017/11/22.
 * @Description
 */
class App : Application() {

    companion object {
        lateinit var appContext: Context
    }


    private val mApplicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        KLog.init(BuildConfig.DEBUG)
        initActivityLifecycleLogs()
    }

    fun initActivityLifecycleLogs() {
        this.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                KLog.v("=========", activity.toString() + "  onActivityCreated")
            }

            override fun onActivityStarted(activity: Activity) {
                KLog.v("=========", activity.toString() + "  onActivityStarted")
            }

            override fun onActivityResumed(activity: Activity) {
                KLog.v("=========", activity.toString() + "  onActivityResumed")
            }

            override fun onActivityPaused(activity: Activity) {
                KLog.v("=========", activity.toString() + "  onActivityPaused")
            }

            override fun onActivityStopped(activity: Activity) {
                KLog.v("=========", activity.toString() + "  onActivityStopped")
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {
                KLog.v("=========", activity.toString() + "  onActivitySaveInstanceState")
            }

            override fun onActivityDestroyed(activity: Activity) {
                KLog.v("=========", activity.toString() + "  onActivityDestroyed")
            }
        })
    }

    fun getApplicationComponent(): ApplicationComponent? = mApplicationComponent

}