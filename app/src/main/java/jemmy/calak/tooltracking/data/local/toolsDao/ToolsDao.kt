/*
 * Copyright (c) 12 - 2020.
 * github: https://github.com/jemmycalak
 * email: jemmy.sapta14@gmail.com
 */

package jemmy.calak.tooltracking.data.local.toolsDao

import androidx.lifecycle.LiveData
import androidx.room.*
import jemmy.calak.tooltracking.model.Tools

@Dao
interface ToolsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertToolsData(tools: List<Tools>)

    @Query("SELECT * FROM tools")
    fun getToolsData(): LiveData<List<Tools>>

    @Update(entity = Tools::class)
    suspend fun updateTools(tools: Tools)

    @Query("SELECT * FROM tools WHERE nameTool =:idTools")
    fun getTools(idTools: String): LiveData<Tools>
}
