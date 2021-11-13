package com.example.gbapp6.data.datasource

import androidx.lifecycle.LiveData
import com.example.gbapp6.data.database.table.DefinitionModel

interface DictionaryLocalDataSource {
    fun insertAllDefinitionModel(list: List<DefinitionModel>)
    fun getAll(): LiveData<List<DefinitionModel>>
    fun getByText(text: String): DefinitionModel
}
