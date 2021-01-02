/*
 * Copyright (c) 12 - 2020.
 * github: https://github.com/jemmycalak
 * email: jemmy.sapta14@gmail.com
 */

package jemmy.calak.tooltracking.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import jemmy.calak.tooltracking.R
import jemmy.calak.tooltracking.data.local.TrackingDatabase
import jemmy.calak.tooltracking.model.Tools
import jemmy.calak.tooltracking.model.User
import kotlinx.coroutines.coroutineScope

class DataTracker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    val listTools = listOf(
        Tools("Wrench", R.drawable.wrench, 6, 0),
        Tools("Cutter", R.drawable.cutter, 15, 0),
        Tools("Pliers", R.drawable.pliers, 12, 0),
        Tools("Screwdriver", R.drawable.screwdriver, 13, 0),
        Tools("Welding machine", R.drawable.weldingmachine, 3, 0),
        Tools("Welding glasses", R.drawable.weldingglasses, 7, 0),
        Tools("Hammer", R.drawable.hammer, 4, 0),
        Tools("Measuring Tape", R.drawable.measuringtape, 9, 0),
        Tools("Alan key set", R.drawable.alankeyset, 4, 0),
        Tools("Air compressor ", R.drawable.aircompressor, 2, 0),
    )

    val listFriends = listOf(
        User("Brian", R.drawable.brian),
        User("Luke", R.drawable.luke),
        User("Letty", R.drawable.letty),
        User("Shaw", R.drawable.shaw),
        User("Parker", R.drawable.parker),
    )

    override suspend fun doWork(): Result = coroutineScope {
        try {
            val database = TrackingDatabase.getInstance(applicationContext)
            database.toolsDao().insertToolsData(listTools)
            database.userDao().insertUser(listFriends)

            Result.success()
        } catch (ex: Exception) {
            Result.failure()
        }
    }
}
