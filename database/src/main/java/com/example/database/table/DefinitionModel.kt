package com.example.database.table

import androidx.room.Embedded
import androidx.room.Relation

data class DefinitionModel(
    @Embedded
    val definition : Definition,
    @Relation(parentColumn = "id", entityColumn = columnNameDefinitionId, entity = Meanings::class)
    val meanings: List<Meanings>?
)
