package com.example.gbapp6.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gbapp6.data.database.dao.DefinitionDao
import com.example.gbapp6.data.database.table.Definition
import com.example.gbapp6.data.database.table.Meanings

@Database(entities = [Definition::class, Meanings::class], version = 1, exportSchema = true)
abstract class DataBase : RoomDatabase() {
    abstract fun definitionDao(): DefinitionDao
}
