package com.example.gbapp6.data.api

import com.example.gbapp6.domain.entity.DataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface DictionaryApi {

    @GET("/api/public/v1/words/search")
    suspend fun search(@Query("search") wordToSearch: String): List<DataModel>

}
