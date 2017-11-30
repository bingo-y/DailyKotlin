package com.bingo.dailykotlin.di.scope


import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Scope

/**
 * @author bingo.
 * @date Create on 2017/11/23.
 * @Description
 */
@Scope
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class PerApp

@Scope
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class PerActivity

@Scope
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class PerFragment

@Scope
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class PerService

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class ContextLife(val value: String = "Application")
