/*
 * Copyright (c) 12 - 2020.
 * github: https://github.com/jemmycalak
 * email: jemmy.sapta14@gmail.com
 */

package jemmy.calak.tooltracking.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import jemmy.calak.tooltracking.data.local.TrackingDatabase
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun getDatabase(application: Application): TrackingDatabase = TrackingDatabase.buildDatabase(application)
}
