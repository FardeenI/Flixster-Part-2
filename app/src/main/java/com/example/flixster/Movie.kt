package com.example.flixster

import android.support.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Movie(
    @SerialName("title")
    val title: String? = null,
    @SerialName("popularity")
    val popularity: Double? = null,
    @SerialName("overview")
    val overview: String? = null,
    @SerialName("release_date")
    val releaseDate: String? = null,
    @SerialName("poster_path")
    val poster: String? = null,
    @SerialName("backdrop_path")
    val backdrop: String? = null
) : java.io.Serializable

@Keep
@Serializable
data class MovieResponse(
    @SerialName("results")
    val results: List<Movie>?
)