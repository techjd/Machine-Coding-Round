package com.techjd.moviedetails.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techjd.moviedetails.data.dto.movies.Movies
import com.techjd.moviedetails.data.repository.MovieRepository
import com.techjd.moviedetails.utils.Constants
import com.techjd.moviedetails.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val movieRepository: MovieRepository
) : ViewModel() {

  private val moviesLiveData: MutableLiveData<NetworkResult<Movies>> = MutableLiveData()
  val movies: LiveData<NetworkResult<Movies>> = moviesLiveData
  private var searchJob: Job? = null

  init {
    getMovies()
  }

  fun getMovies(query: String = Constants.DEFAULT_QUERY) {
    searchJob?.cancel()
    searchJob = viewModelScope.launch {
      moviesLiveData.value = NetworkResult.Loading()
      val moviesResponse = movieRepository.getMovies(query)
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