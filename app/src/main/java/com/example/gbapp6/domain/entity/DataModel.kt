package com.example.gbapp6.domain.entity

import com.google.gson.annotations.SerializedName

data class DataModel(
    @field:SerializedName("text") val text: String?,
    @field:SerializedName("meanings") val meanings: List<Meanings>?
)
