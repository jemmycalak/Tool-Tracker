/*
 * Copyright (c) 12 - 2020.
 * github: https://github.com/jemmycalak
 * email: jemmy.sapta14@gmail.com
 */

package jemmy.calak.tooltracking.data.repository

import jemmy.calak.tooltracking.data.local.TrackingDatabase
import javax.inject.Inject

class UserRepository @Inject constructor(private val db: TrackingDatabase) {

    fun getListUsers() = db.userDao().getListUser()
    fun getDetailUser(idUser: String) = db.userDao().getUser(idUser)
}
