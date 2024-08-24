package com.paymob.movies.modules.listing.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.paymob.movies.BuildConfig
import com.paymob.movies.R
import com.paymob.movies.databinding.MovieListViewItemBinding
import com.paymob.movies.modules.common_views.base.BaseAdapter
import com.paymob.movies.modules.listing.domain.entites.MovieEntity

class MoviesAdapter(private val onMovieClicked: (MovieEntity) -> Unit,
                    private val onWishlistClicked: (MovieEntity) -> Unit) : BaseAdapter<MovieListViewItemBinding, MovieEntity>() {
    override fun createBinding(parent: ViewGroup, viewType: Int) = MovieListViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    override fun bind(binding: MovieListViewItemBinding, position: Int) {
        val currentItem = getItem(position)
        binding.apply {

            textViewTitle.text = currentItem.movieName
            currentItem.releaseDate.also { tvReleaseDate.text = it }
            "${currentItem.rating}".also { tvRating.text = it }

            Glide.with(imageViewPoster.context)
                .load("${BuildConfig.BASE_IMAGES_URL}${currentItem.moviePoster}")
                .error(R.drawable.ic_paymop_logo)
                .into(imageViewPoster)

            binding.layoutRoot.setOnClickListener {
                onMovieClicked.invoke(currentItem)
            }

            binding.favoriteIcon.setOnClickListener {
                onWishlistClicked.invoke(currentItem)
            }

            binding.favoriteIcon.setImageResource(
                if (currentItem.isFavorite) {
                    R.drawable.ic_wishlist_selected
                } else {
                    R.drawable.ic_wishlist_unselected
                }
            )
        }
    }
}