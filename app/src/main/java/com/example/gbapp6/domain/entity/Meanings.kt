package com.example.gbapp6.domain.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Meanings(
    @SerializedName("id") val id: Int,
    @SerializedName("translation") val translation: Translation?,
) : Parcelable
