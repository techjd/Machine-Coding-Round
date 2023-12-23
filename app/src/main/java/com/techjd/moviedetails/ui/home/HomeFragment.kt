package com.techjd.moviedetails.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import com.techjd.moviedetails.R
import com.techjd.moviedetails.databinding.FragmentHomeBinding
import com.techjd.moviedetails.ui.detail.MovieDetailFragment
import com.techjd.moviedetails.utils.NetworkResult
import com.techjd.moviedetails.utils.NetworkResult.Error
import com.techjd.moviedetails.utils.NetworkResult.Loading
import com.techjd.moviedetails.utils.NetworkResult.Success
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

  private var _binding: FragmentHomeBinding? = null
  private val binding get() = _binding!!

  @Inject
  lateinit var glide: RequestManager

  private val homeViewModel: HomeViewModel by viewModels()

  private lateinit var moviesListAdapter: MoviesListAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentHomeBinding.inflate(inflater, container, false)
    val view = binding.root
    return view
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)

    binding.editTextText.addTextChangedListener {
      it?.let {
        if (it.trim().isNotEmpty() && it.trim().isNotBlank()) {
          homeViewModel.getMovies(it.toString().trim())
        }
      }
    }

    homeViewModel.movies.observe(viewLifecycleOwner) {
      when(it) {
        is Success -> {
          it.data?.let { movies ->
            moviesListAdapter = MoviesListAdapter(glide) {
              findNavController().navigate(R.id.movieDetailFragment, )
              val bundle = Bundle()
              bundle.putString("movieId", it)
              findNavController().navigate(R.id.movieDetailFragment, bundle)
            }
            binding.recyclerView.adapter = moviesListAdapter
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
            moviesListAdapter.submitList(movies.search)
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

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}