package com.example.gbapp6.domain.repository

import com.example.gbapp6.data.datasource.DictionaryDataSource
import com.example.gbapp6.domain.entity.DataModel
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class DictionaryRepositoryImpl @Inject constructor(
    private val dictionaryDataSource: DictionaryDataSource
) : DictionaryRepository {

    override fun getData(word: String): Observable<List<DataModel>> =
        dictionaryDataSource.getData(word)

}
