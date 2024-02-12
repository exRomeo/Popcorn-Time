package com.example.popcorntime.modules.movie_details.data.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class SpokenLanguageDataModel(
    @SerializedName("english_name")
    val englishName: String? = null,
    @SerializedName("iso_639_1")
    val iso639_1: String? = null,
    @SerializedName("name")
    val name: String? = null
)
