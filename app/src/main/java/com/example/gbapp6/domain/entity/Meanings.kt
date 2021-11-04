package com.example.gbapp6.domain.entity

import com.google.gson.annotations.SerializedName

data class Meanings(
    @field:SerializedName("translation") val translation: Translation?,
)
