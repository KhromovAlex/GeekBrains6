package com.example.gbapp6.data.datasource

import com.example.gbapp6.domain.entity.DataModel
import io.reactivex.rxjava3.core.Observable

interface DictionaryDataSource {

    fun getData(word: String): Observable<List<DataModel>>

}
