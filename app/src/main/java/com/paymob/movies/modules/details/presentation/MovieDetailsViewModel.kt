package com.paymob.movies.modules.details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paymob.movies.db.api.ApiResult
import com.paymob.movies.db.local.datastore.DataStoreManager
import com.paymob.movies.modules.details.data.params.MovieDetailsParams
import com.paymob.movies.modules.details.domain.entity.MovieDetailsEntity
import com.paymob.movies.modules.details.domain.usecases.MovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    val movieDetailsUseCase: MovieDetailsUseCase,
    val dataStoreManager: DataStoreManager
) : ViewModel()
{
    lateinit var movieDetails : MovieDetailsEntity
    var isWishlist : Boolean = false;

    private val _movieDetailsState = MutableStateFlow<MovieDetailsState>(
        MovieDetailsState.Loading(
            false
        )
    )
    val movieDetailsState: StateFlow<MovieDetailsState> = _movieDetailsState

    fun getMovieDetailsById(movieId : Int) {

        viewModelScope.launch {

            val movieDetailsAParams = MovieDetailsParams(movieId)

            when (val movieDetailsResult = movieDetailsUseCase(movieDetailsAParams)) {
                is ApiResult.Success -> {
                    movieDetails = movieDetailsResult.data
                    isWishlist = movieDetails.isWishlist
                    _movieDetailsState.value =
                        MovieDetailsState.Success(movieDetails)
                }
                is ApiResult.ApiError -> {
                    _movieDetailsState.value = MovieDetailsState.ApiError("")
                }
                is ApiResult.InternetError -> {
                    _movieDetailsState.value = MovieDetailsState.InternetError
                }
            }
        }
    }

    fun toggleFavoriteStatus() {
        _movieDetailsState.value = MovieDetailsState.Loading(true)

        if (!isWishlist) {
            dataStoreManager.addMovieToWishlist(movieDetails.id.toString(), viewModelScope) { success ->
                if(!success){
                    _movieDetailsState.value = MovieDetailsState.WishlistError("Movie is already in the wishlist")
                }
                else {
                    isWishlist = !isWishlist
                    _movieDetailsState.value = MovieDetailsState.WishlistToggleStateSuccess
                }
            }
        } else {
            dataStoreManager.removeMovieFromWishlist(movieDetails.id.toString(), viewModelScope) { success ->
                if(!success){
                    _movieDetailsState.value = MovieDetailsState.WishlistError("Movie was not in the wishlist")
                }
                else {
                    isWishlist = !isWishlist
                    _movieDetailsState.value = MovieDetailsState.WishlistToggleStateSuccess
                }
            }
        }
        _movieDetailsState.value = MovieDetailsState.Loading(false)
    }
}