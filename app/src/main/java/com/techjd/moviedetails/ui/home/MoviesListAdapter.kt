package com.techjd.moviedetails.ui.home

import android.graphics.Movie
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.techjd.moviedetails.data.dto.movies.Movies
import com.techjd.moviedetails.data.dto.movies.Search
import com.techjd.moviedetails.databinding.ItemMoviesBinding
import com.techjd.moviedetails.ui.home.MoviesListAdapter.MoviesViewHolder

class MoviesListAdapter(
  private val glide: RequestManager,
  val onClick :(String) -> Unit
): ListAdapter<Search, MoviesViewHolder>(MoviesDiffUtil()) {

  inner class MoviesViewHolder(
    private val moviesBinding: ItemMoviesBinding
  ): RecyclerView.ViewHolder(moviesBinding.root) {
    fun bind(
      search: Search
    ) {
      with(search) {
        if (poster == "N/A") {
          moviesBinding.movieImg.visibility = View.GONE
        } else {
          Glide.with(itemView).load(poster).into(moviesBinding.movieImg)
        }
        moviesBinding.movieTitle.text = title
        moviesBinding.movieYear.text = year
        moviesBinding.moviesLayout.setOnClickListener {
          onClick(imdbID)
        }
      }
    }
  }


  class MoviesDiffUtil: DiffUtil.ItemCallback<Search>() {
    override fun areItemsTheSame(
      oldItem: Search,
      newItem: Search
    ): Boolean = oldItem.imdbID == newItem.imdbID

    override fun areContentsTheSame(
      oldItem: Search,
      newItem: Search
    ): Boolean = oldItem == newItem
  }

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): MoviesViewHolder {
    val view = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return MoviesViewHolder(view)
  }

  override fun onBindViewHolder(
    holder: MoviesViewHolder,
    position: Int
  ) {
    val movie = getItem(position)
    movie?.let {
      holder.bind(it)
    }
  }
}