package com.example.gbapp6.domain.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataModel(
    @SerializedName("id") val id: Int,
    @SerializedName("text") val text: String?,
    @SerializedName("meanings") val meanings: List<Meanings>?
) : Parcelable
