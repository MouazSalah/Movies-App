package com.paymob.movies.modules.listing.presentation

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.paymob.movies.modules.common_views.apierror.BottomSheetServerError
import com.paymob.movies.extesnion.castToActivity
import com.banquemisr.currency.ui.ui.base.BaseFragment
import com.paymob.movies.core.BaseApp
import com.paymob.movies.modules.common_views.base.MainActivity
import com.paymob.movies.modules.common_views.nointernet.NoInternetActivity
import com.paymob.movies.databinding.FragmentMoviesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : BaseFragment<FragmentMoviesBinding>() {

    private val moviesAdapter by lazy { MoviesAdapter(
        loadNewPage = {

        },
        onMovieClicked = { movieEntity ->
                Toast.makeText(BaseApp.instance.baseContext, "${movieEntity.movieName} is clicked!", Toast.LENGTH_LONG).show()
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
                    is MoviesListingState.Shimmer -> {
                        binding.shimmerLayout.isVisible = false;
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

            castToActivity<MainActivity> {

                it?.mBinding?.apply {
                    clToolbar.isVisible = true
                    tvTitle.text = "Popular Movies"
                    btnBack.isVisible = false
                }
            }
        }

        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().popBackStack()
        }
        callback.isEnabled = true
    }
}