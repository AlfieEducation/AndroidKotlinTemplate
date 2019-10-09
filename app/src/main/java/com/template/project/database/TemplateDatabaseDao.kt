package com.template.project.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TemplateDatabaseDao {

    @Insert
    fun insert(temp: TemplateEntity)

    @Update
    fun update(temp: TemplateEntity)

    @Query("SELECT * from template_table WHERE entityId = :key")
    fun get(key: Long): TemplateEntity?

    @Query("DELETE FROM template_table")
    fun clear()

    @Query("SELECT * FROM template_table ORDER BY entityId DESC")
    fun getAllEntities(): LiveData<List<TemplateEntity>>

    @Query("SELECT * FROM template_table ORDER BY entityId DESC LIMIT 1")
    fun getLastEntity(): TemplateEntity?

}