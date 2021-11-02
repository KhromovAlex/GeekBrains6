package com.example.gbapp6.domain.repository

import com.example.gbapp6.domain.entity.DataModel

interface DictionaryRepository {

    suspend fun getData(word: String): List<DataModel>

}
