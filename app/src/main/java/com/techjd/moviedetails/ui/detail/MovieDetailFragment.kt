package com.techjd.moviedetails.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.techjd.moviedetails.R
import com.techjd.moviedetails.databinding.FragmentHomeBinding
import com.techjd.moviedetails.databinding.FragmentMovieDetailBinding
import com.techjd.moviedetails.ui.home.MoviesListAdapter
import com.techjd.moviedetails.utils.NetworkResult.Error
import com.techjd.moviedetails.utils.NetworkResult.Loading
import com.techjd.moviedetails.utils.NetworkResult.Success
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

  val movieDetailFragmentArgs: MovieDetailFragmentArgs by navArgs()

  private var _binding: FragmentMovieDetailBinding? = null
  private val binding get() = _binding!!

  private val movieDetailViewModel: MovieDetailViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)

    movieDetailFragmentArgs.movieId?.let {
      Log.d("ID", "onViewCreated: ${it}")
      movieDetailViewModel.getDetailMovie(it)
    }

    movieDetailViewModel.movies.observe(viewLifecycleOwner) {
      when(it) {
        is Success -> {
          it.data?.let { movies ->
            binding.movieName.text = movies.title
          }
        }
        is Error -> {
          // left
        }
        is Loading -> {

        }
      }
    }
  }
}