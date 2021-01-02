/*
 * Copyright (c) 12 - 2020.
 * github: https://github.com/jemmycalak
 * email: jemmy.sapta14@gmail.com
 */

package jemmy.calak.tooltracking.data.local.borrowDao

import androidx.lifecycle.LiveData
import androidx.room.*
import jemmy.calak.tooltracking.model.Borrowed
import jemmy.calak.tooltracking.model.TransactionModel
import jemmy.calak.tooltracking.model.UserBorrowed

@Dao
interface BorrowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBorrow(data: Borrowed)

    @Transaction
    @Query("SELECT * FROM Borrowed")
    fun getAllBorrewedTools():LiveData<List<TransactionModel>>

    @Query("SELECT * FROM Borrowed WHERE idTools = :idTool")
    fun getFriendsThatBorrowedTools(idTool: Int): LiveData<List<Borrowed>>

    @Transaction
    @Query("SELECT * FROM user WHERE name IN (SELECT idUser FROM borrowed WHERE idTools =:idTool)")
    fun getListUserByToolID(idTool: String): LiveData<List<UserBorrowed>>

    @Transaction
    @Query("SELECT * FROM Borrowed WHERE idUser =:user")
    fun getListBorrowedByUserId(user: String): LiveData<List<TransactionModel>>

    @Update(entity = Borrowed::class)
    suspend fun updateBorrowed(borrowed: Borrowed)
}
