package com.example.gbapp6.data.database.table

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity

@Entity(tableName = tableName, primaryKeys = [columnNameId, columnNameDefinitionId])
data class Meanings (
    @ColumnInfo(name = columnNameId)
    val id: Int,
    @ColumnInfo(name = columnNameDefinitionId)
    val definitionId: Int,
    @Embedded
    val translation: Translation?,
)

private const val tableName = "meanings_table"
private const val columnNameId = "id"
const val columnNameDefinitionId = "definition_id"
