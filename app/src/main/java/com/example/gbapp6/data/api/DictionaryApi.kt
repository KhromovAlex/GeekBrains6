package com.example.gbapp6.data.api

import com.example.gbapp6.domain.entity.DataModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface DictionaryApi {

    @GET("/api/public/v1/words/search")
    fun search(@Query("search") wordToSearch: String): Observable<List<DataModel>>

}
