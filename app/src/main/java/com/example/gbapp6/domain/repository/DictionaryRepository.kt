package com.example.gbapp6.domain.repository

import com.example.gbapp6.domain.entity.DataModel
import io.reactivex.rxjava3.core.Observable

interface DictionaryRepository {

    fun getData(word: String): Observable<List<DataModel>>

}
