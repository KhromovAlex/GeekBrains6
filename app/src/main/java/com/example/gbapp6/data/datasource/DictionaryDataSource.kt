package com.example.gbapp6.data.datasource

import com.example.gbapp6.domain.entity.DataModel

interface DictionaryDataSource {

    suspend fun getData(word: String): List<DataModel>

}
