package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.dao.DefinitionDao
import com.example.database.table.Definition
import com.example.database.table.Meanings

@Database(entities = [Definition::class, Meanings::class], version = 1, exportSchema = true)
abstract class DataBase : RoomDatabase() {
    abstract fun definitionDao(): DefinitionDao
}
