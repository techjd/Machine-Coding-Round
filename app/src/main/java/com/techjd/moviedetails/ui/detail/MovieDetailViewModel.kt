package com.techjd.moviedetails.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techjd.moviedetails.data.dto.moviedetail.MovieDetail
import com.techjd.moviedetails.data.dto.movies.Movies
import com.techjd.moviedetails.data.repository.MovieRepository
import com.techjd.moviedetails.utils.Constants
import com.techjd.moviedetails.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
  private val movieRepository: MovieRepository
):  ViewModel() {

  private val moviesLiveData: MutableLiveData<NetworkResult<MovieDetail>> = MutableLiveData()
  val movies: LiveData<NetworkResult<MovieDetail>> = moviesLiveData

  fun getDetailMovie(id: String) {
    viewModelScope.launch {
      moviesLiveData.value = NetworkResult.Loading()
      val moviesResponse = movieRepository.getMovieDetail(id)
      with(moviesResponse) {
        if (isSuccessful) {
          val movies = body()
          movies?.let {
            moviesLiveData.value = NetworkResult.Success(it)
          } ?: run {
            moviesLiveData.value = NetworkResult.Error("Something Went Wrong")
          }
        } else {
          // decode error
          moviesLiveData.value = NetworkResult.Error("Error Fetching Movies")
        }
      }
    }
  }
}