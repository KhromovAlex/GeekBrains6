package com.example.gbapp6.data.datasource

import com.example.gbapp6.data.api.DictionaryApi
import com.example.gbapp6.domain.entity.DataModel
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class DictionaryDataSourceImpl @Inject constructor(
    private val dictionaryApi: DictionaryApi
) : DictionaryDataSource {

    override fun getData(word: String): Observable<List<DataModel>> =
        dictionaryApi.search(word)

}
