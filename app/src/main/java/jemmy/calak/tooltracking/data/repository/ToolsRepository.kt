/*
 * Copyright (c) 12 - 2020.
 * github: https://github.com/jemmycalak
 * email: jemmy.sapta14@gmail.com
 */

package jemmy.calak.tooltracking.data.repository

import jemmy.calak.tooltracking.data.local.TrackingDatabase
import jemmy.calak.tooltracking.model.Tools
import javax.inject.Inject

class ToolsRepository @Inject constructor(private val db: TrackingDatabase) {

    fun getListTools() = db.toolsDao().getToolsData()

    fun getTools(idTools: String) = db.toolsDao().getTools(idTools)

    suspend fun updateTools(tools: Tools) = db.toolsDao().updateTools(tools)

}