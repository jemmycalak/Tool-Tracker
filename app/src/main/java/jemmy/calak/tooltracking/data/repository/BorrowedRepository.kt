/*
 * Copyright (c) 12 - 2020.
 * github: https://github.com/jemmycalak
 * email: jemmy.sapta14@gmail.com
 */

package jemmy.calak.tooltracking.data.repository

import jemmy.calak.tooltracking.data.local.TrackingDatabase
import jemmy.calak.tooltracking.model.Borrowed
import javax.inject.Inject

class BorrowedRepository @Inject constructor(private val db: TrackingDatabase) {

    suspend fun onInsertBorrowed(model: Borrowed) = db.borrowedDao().insertBorrow(model)

    fun getListUserByIdTools(idTool: String) = db.borrowedDao().getListUserByToolID(idTool)

    fun getListBorrower() = db.borrowedDao().getAllBorrewedTools()

    fun getListBorrowedByUserId(user: String) = db.borrowedDao().getListBorrowedByUserId(user)

    suspend fun updateBorrowed(borrowed: Borrowed) = db.borrowedDao().updateBorrowed(borrowed)
}
