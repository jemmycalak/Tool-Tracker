/*
 * Copyright (c) 12 - 2020.
 * github: https://github.com/jemmycalak
 * email: jemmy.sapta14@gmail.com
 */

package jemmy.calak.tooltracking.model

import androidx.room.Embedded
import androidx.room.Relation

class TransactionModel(

    @Embedded
    val borrowed: Borrowed,

    @Relation(parentColumn = "idUser", entityColumn = "name", entity = User::class)
    val user:User,

    @Relation(parentColumn = "idTools", entityColumn = "nameTool", entity = Tools::class)
    val tool:Tools,


//    val id:Int,
//    val idUser: String,
//    val idTools: String,
//    val value: Int,
//    val name: String,
//    val imageUrl:String,
//    val nameTool:String ,
//    val imageUri:Int,
//    val quantity:Int,
//    val quantityBorrowed:Int
)
