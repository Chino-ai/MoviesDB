package com.example.movies.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movies(
    val overview: String,
    val originalLanguage: String,
    val title: String,
    val posterPath: String,
    val releaseDate: String,
    val popularity: Double,
    val id: Int,
) : Parcelable