package com.example.gbapp6.domain.repository

import com.example.gbapp6.data.datasource.DictionaryDataSource
import com.example.gbapp6.domain.entity.DataModel

class DictionaryRepositoryImpl(
    private val dictionaryDataSource: DictionaryDataSource
) : DictionaryRepository {

    override suspend fun getData(word: String): List<DataModel> =
        dictionaryDataSource.getData(word)

}
