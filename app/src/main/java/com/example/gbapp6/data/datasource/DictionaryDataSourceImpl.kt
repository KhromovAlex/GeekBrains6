package com.example.gbapp6.data.datasource

import com.example.gbapp6.data.api.DictionaryApi
import com.example.gbapp6.domain.entity.DataModel

class DictionaryDataSourceImpl(
    private val dictionaryApi: DictionaryApi
) : DictionaryDataSource {

    override suspend fun getData(word: String): List<DataModel> =
        dictionaryApi.search(word)

}
