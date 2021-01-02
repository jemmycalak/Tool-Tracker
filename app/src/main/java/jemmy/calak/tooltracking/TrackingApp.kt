/*
 * Copyright (c) 12 - 2020.
 * github: https://github.com/jemmycalak
 * email: jemmy.sapta14@gmail.com
 */

package jemmy.calak.tooltracking

import android.app.Application
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.android.HiltAndroidApp
import jemmy.calak.tooltracking.worker.DataTracker

@HiltAndroidApp
class TrackingApp : Application() {
    override fun onCreate() {
        super.onCreate()

        val request = OneTimeWorkRequestBuilder<DataTracker>().build()
        WorkManager.getInstance(this).enqueue(request)
    }
}
