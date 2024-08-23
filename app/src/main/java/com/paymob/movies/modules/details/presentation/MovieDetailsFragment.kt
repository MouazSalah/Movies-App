package com.paymob.movies.modules.details.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.paymob.movies.modules.common_views.apierror.BottomSheetServerError
import com.paymob.movies.extesnion.castToActivity
import com.banquemisr.currency.ui.ui.base.BaseFragment
import com.bumptech.glide.Glide
import com.paymob.movies.BuildConfig
import com.paymob.movies.R
import com.paymob.movies.databinding.FragmentMovieDetailsBinding
import com.paymob.movies.modules.common_views.base.MainActivity
import com.paymob.movies.modules.details.domain.entity.MovieDetailsEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment<FragmentMovieDetailsBinding>()
{
    private val movieDetailsArgs: MovieDetailsFragmentArgs by navArgs()

    private val viewModel : MovieDetailsViewModel by viewModels()
    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentMovieDetailsBinding {
        return FragmentMovieDetailsBinding.inflate(inflater, container, false)
    }

    override fun onFragmentReady() {

        val movieID = movieDetailsArgs.movieId
        viewModel.getMovieDetailsById(movieId = movieID)

        initViews()
        initObservers()
    }

    private fun initObservers() {
        lifecycleScope.launch {
            viewModel.movieDetailsState.collect{ state ->
                when (state) {
                    is MovieDetailsState.Success -> {
                        fillMovieDetailsToViews(state.movieDetails)
                    }
                    is MovieDetailsState.Shimmer -> {

                    }
                    is MovieDetailsState.Loading -> {
                        castToActivity<MainActivity> { activity ->
                            activity?.mBinding?.progressBar?.isVisible = state.isShow
                        }
                    }
                    else -> {
                        BottomSheetServerError().show(requireActivity().supportFragmentManager, null)
                    }
                }
            }
        }
    }

    private fun fillMovieDetailsToViews(movieDetails : MovieDetailsEntity) {
        binding.apply {

            Glide.with(imageViewPoster.context)
                .load("${BuildConfig.BASE_IMAGES_URL}${movieDetails.moviePoster}")
                .into(imageViewPoster)

            textViewTitle.text = movieDetails.name

            textViewOverview.text = movieDetails.overview

            imgWishlist.setImageResource(
                if (movieDetails.isWishlist) {
                    R.drawable.ic_wishlist_selected
                } else {
                    R.drawable.ic_wishlist_unselected
                }
            )

            "Release Date: ${movieDetails.releaseDate}".also { textViewReleaseDate.text = it }

            "Genres: ${movieDetails.genres}".also { textViewGenres.text = it }

            "Runtime: ${movieDetails.runtime} minutes".also { textViewRuntime.text = it }

            "IMDb Rating: ${movieDetails.rating}".also { textViewIMDbRating.text = it }

            "Production Companies: ${movieDetails.productionCompanies}".also { textViewProductionCompanies.text = it }

            "Production Countries: ${movieDetails.productionCountries}".also { textViewProductionCountries.text = it }

            "Status: ${movieDetails.status}".also { textViewStatus.text = it }
        }
    }

    private fun initViews() {

        binding.apply {

            imgWishlist.setOnClickListener {

            }

            castToActivity<MainActivity> {

                it?.mBinding?.apply {
                    clToolbar.isVisible = true
                    tvTitle.text = getString(R.string.movie_details)
                    btnBack.isVisible = false
                }
            }
        }


        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            whenBackButtonClicked()
        }
        callback.isEnabled = true
    }

    private fun whenBackButtonClicked() {
        val result = Bundle().apply {
            putBoolean("isFavoriteStateChanged", false)
        }
        setFragmentResult("movie_details_request_key", result)
        findNavController().popBackStack()
    }
}