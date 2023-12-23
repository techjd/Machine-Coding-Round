package com.techjd.moviedetails.data.repository

import com.techjd.moviedetails.data.api.MovieApiInterface
import com.techjd.moviedetails.utils.Constants
import javax.inject.Inject

class MovieRepository @Inject constructor(
  private val movieApiInterface: MovieApiInterface
) {

  suspend fun getMovies(query: String) = movieApiInterface.getMovies(query)

  suspend fun getMovieDetail(
    id: String
  ) = movieApiInterface.getMovieDetail(id)
}