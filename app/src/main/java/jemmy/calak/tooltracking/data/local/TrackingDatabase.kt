/*
 * Copyright (c) 12 - 2020.
 * github: https://github.com/jemmycalak
 * email: jemmy.sapta14@gmail.com
 */

package jemmy.calak.tooltracking.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import jemmy.calak.tooltracking.BuildConfig
import jemmy.calak.tooltracking.data.local.borrowDao.BorrowDao
import jemmy.calak.tooltracking.data.local.toolsDao.ToolsDao
import jemmy.calak.tooltracking.data.local.userDao.UserDao
import jemmy.calak.tooltracking.model.Borrowed
import jemmy.calak.tooltracking.model.Tools
import jemmy.calak.tooltracking.model.User

@Database(
    entities = [
        Tools::class,
        User::class,
        Borrowed::class
    ], version = BuildConfig.DB_VERSION, exportSchema = false
)
abstract class TrackingDatabase : RoomDatabase() {

    abstract fun toolsDao(): ToolsDao
    abstract fun userDao(): UserDao
    abstract fun borrowedDao(): BorrowDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: TrackingDatabase? = null

        fun getInstance(context: Context): TrackingDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        fun buildDatabase(contex: Context) =
            Room.databaseBuilder(
                contex,
                TrackingDatabase::class.java,
                BuildConfig.DB_NAME
            ).build()
    }
}
