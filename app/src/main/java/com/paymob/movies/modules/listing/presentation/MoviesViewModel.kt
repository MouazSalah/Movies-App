package com.paymob.movies.modules.listing.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paymob.movies.db.api.ApiResult
import com.paymob.movies.modules.listing.data.params.MoviesListingParams
import com.paymob.movies.modules.listing.domain.entites.MovieEntity
import com.paymob.movies.modules.listing.domain.entites.MoviesListingEntity
import com.paymob.movies.modules.listing.domain.usecases.MoviesListingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesListingUseCase: MoviesListingUseCase,
) : ViewModel()
{
    private val _moviesListingState = MutableStateFlow<MoviesListingState>(
        MoviesListingState.Loading(
            false
        )
    )
    val moviesListingState: StateFlow<MoviesListingState> = _moviesListingState

    var moviesParams : MoviesListingParams = MoviesListingParams()

    var movies: MoviesListingEntity? = null

    init {
        fetchMovies()
    }

     fun fetchMovies() {
        viewModelScope.launch {
            when (val moviesResult = moviesListingUseCase(moviesParams)) {
                is ApiResult.Success -> {
                    movies = moviesResult.data
                    _moviesListingState.value =
                        MoviesListingState.Success(movies?.movies as ArrayList<MovieEntity>)
                }
                is ApiResult.ApiError -> {
                    _moviesListingState.value = MoviesListingState.ApiError("")
                }
                is ApiResult.InternetError -> {
                    _moviesListingState.value = MoviesListingState.InternetError
                }
            }
        }
    }
}