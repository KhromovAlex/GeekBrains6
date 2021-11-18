package com.example.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.database.table.Definition
import com.example.database.table.DefinitionModel
import com.example.database.table.Meanings

@Dao
interface DefinitionDao {
    @Transaction
    @Query("SELECT * FROM definition_table")
    fun getAll(): LiveData<List<DefinitionModel>>

    @Transaction
    @Query("SELECT * FROM definition_table WHERE text LIKE :text LIMIT 1")
    fun getByText(text: String): DefinitionModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDefinition(definition: Definition)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMeanings(list: List<Meanings>)

    @Transaction
    fun insertAllDefinitionModel(list: List<DefinitionModel>) {
        list.forEach { model ->
            insertDefinition(model.definition)
            model.meanings?.let { insertAllMeanings(it) }
        }
    }


}
