/*
 * Copyright (c) 12 - 2020.
 * github: https://github.com/jemmycalak
 * email: jemmy.sapta14@gmail.com
 */

package jemmy.calak.tooltracking.data.local.userDao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import jemmy.calak.tooltracking.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(list: List<User>)

    @Query("SELECT * FROM user")
    fun getListUser(): LiveData<List<User>>

    @Query("SELECT * FROM user WHERE name=:idUser")
    fun getUser(idUser: String): LiveData<User>
}
