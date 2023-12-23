package com.techjd.moviedetails.data.dto.movies


import com.google.gson.annotations.SerializedName

data class Movies(
    @SerializedName("Response")
    val response: String,
    @SerializedName("Search")
    val search: List<Search>,
    @SerializedName("totalResults")
    val totalResults: String
)