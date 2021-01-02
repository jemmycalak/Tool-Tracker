/*
 * Copyright (c) 12 - 2020.
 * github: https://github.com/jemmycalak
 * email: jemmy.sapta14@gmail.com
 */

package jemmy.calak.tooltracking.model

import androidx.room.Embedded
import androidx.room.Relation

class UserBorrowed(
    @Embedded
    val user: User,

    @Relation(parentColumn = "name", entityColumn = "idUser",)
    val userBorrowed:Borrowed,
)
