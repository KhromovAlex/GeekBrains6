package com.example.gbapp6.data.datasource

import androidx.lifecycle.LiveData
import com.example.gbapp6.data.database.DataBase
import com.example.gbapp6.data.database.table.DefinitionModel

class DictionaryLocalDataSourceImpl(
    private val dataBase: DataBase
) : DictionaryLocalDataSource {
    override fun insertAllDefinitionModel(list: List<DefinitionModel>) =
        dataBase.definitionDao().insertAllDefinitionModel(list)

    override fun getAll(): LiveData<List<DefinitionModel>> =
        dataBase.definitionDao().getAll()

    override fun getByText(text: String): DefinitionModel =
        dataBase.definitionDao().getByText(text)
}
