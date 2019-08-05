package com.template.project.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "template_table")
data class TemplateEntity(
    @PrimaryKey(autoGenerate = true)
    var entityId: Long = 0L,

    @ColumnInfo(name = "start_time_field")
    val timeField: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "integer_field")
    var intField: Int = -1,

    @ColumnInfo(name = "string_field")
    var stringField: String = ""
    )