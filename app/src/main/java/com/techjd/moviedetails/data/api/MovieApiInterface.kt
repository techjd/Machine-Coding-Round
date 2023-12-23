package com.techjd.moviedetails.data.api

import com.techjd.moviedetails.data.dto.moviedetail.MovieDetail
import com.techjd.moviedetails.data.dto.movies.Movies
import com.techjd.moviedetails.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiInterface {

  @GET("${Constants.BASE_URL}/?apikey=${Constants.API_KEY}")
  suspend fun getMovies(
    @Query("s") query: String
  ): Response<Movies>

  @GET("${Constants.BASE_URL}/")
  suspend fun getMovieDetail(
    @Query("i") id: String,
    @Query("apikey") apiKey: String = Constants.API_KEY
  ): Response<MovieDetail>
}