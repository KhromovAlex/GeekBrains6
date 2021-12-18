package com.example.database.table

import androidx.room.*

@Entity(tableName = tableName)
data class Definition(
    @PrimaryKey
    @ColumnInfo(name = columnNameId)
    val id: Int,
    @ColumnInfo(name = columnNameText)
    val text: String?,
)

private const val tableName = "definition_table"
private const val columnNameId = "id"
private const val columnNameText = "text"
