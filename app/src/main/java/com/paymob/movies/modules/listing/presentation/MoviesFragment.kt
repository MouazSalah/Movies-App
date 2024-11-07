package com.paymob.movies.modules.listing.presentation

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.paymob.movies.extesnion.castToActivity
import com.paymob.movies.modules.common_views.base.BaseFragment
import com.paymob.movies.R
import com.paymob.movies.modules.common_views.base.MainActivity
import com.example.ui.theme.common_views.nointernet.NoInternetActivity
import com.paymob.movies.databinding.FragmentMoviesBinding
import com.paymob.movies.modules.common_views.apierror.BottomSheetServerError
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : BaseFragment<FragmentMoviesBinding>() {

    private val moviesAdapter by  lazy { MoviesAdapter(
        onMovieClicked = { movieEntity ->
             val movieDetailsAction = MoviesFragmentDirections.actionToMovieDetails(movieEntity.id)
             findNavController().navigate(movieDetailsAction)
        },
        onWishlistClicked = { movieEntity ->
            viewModel.toggleFavoriteStatus(movieEntity)
        },
    ) }

    private val viewModel : MoviesViewModel by viewModels()
    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentMoviesBinding {
        return FragmentMoviesBinding.inflate(inflater, container, false)
    }

    private val noInternetForResultActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            viewModel.fetchMovies()
        }
    }

    override fun onFragmentReady() {
        initViews()
        initObservers()

        setFragmentResultListener("movie_details_request_key") { requestKey, bundle ->
            if (requestKey == "movie_details_request_key") {
                viewModel.updateMoviesList()
            }
        }
    }

    private fun initObservers() {
        lifecycleScope.launch {
            viewModel.moviesListingState.collect{ state ->
                when (state) {
                    is MoviesListingState.Success -> {
                        binding.rvMovies.isVisible = true;
                        moviesAdapter.setList(state.movies)
                    }
                    is MoviesListingState.ApiError -> {
                        BottomSheetServerError().show(requireActivity().supportFragmentManager, null)
                    }
                    MoviesListingState.InternetError -> {
                        val intent = Intent(requireActivity(), NoInternetActivity::class.java)
                        noInternetForResultActivity.launch(intent)
                    }
                    is MoviesListingState.WishlistError -> {
                        Toast.makeText(this@MoviesFragment.context, state.errorMessage, Toast.LENGTH_LONG).show()
                    }
                    is MoviesListingState.Shimmer -> {
                        binding.shimmerLayout.isVisible = state.isShow
                    }
                    is MoviesListingState.Loading -> {
                        binding.progressCircular.isVisible = state.isShow
                    }
                }
            }
        }
    }

    private fun initViews() {

        binding.apply {

            rvMovies.adapter = moviesAdapter

            castToActivity<MainActivity> { activity ->

                activity?.setToolbarTitle(getString(R.string.popular_movies))

                activity?.showToolbar(true)
                activity?.showBackBtn(false)
            }
        }

        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().popBackStack()
        }
        callback.isEnabled = true
    }
}