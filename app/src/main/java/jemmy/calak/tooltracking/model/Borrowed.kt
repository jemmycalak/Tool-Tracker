/*
 * Copyright (c) 12 - 2020.
 * github: https://github.com/jemmycalak
 * email: jemmy.sapta14@gmail.com
 */

package jemmy.calak.tooltracking.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(entity = User::class, parentColumns = ["name"], childColumns = ["idUser"]),
        ForeignKey(entity = Tools::class, parentColumns = ["nameTool"], childColumns = ["idTools"])
    ],
    indices = [Index("id"), Index("idUser"), Index("idTools")]
)
class Borrowed(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    var idUser: String,
    var idTools: String,
    var value: Int,
    var status: String
)
