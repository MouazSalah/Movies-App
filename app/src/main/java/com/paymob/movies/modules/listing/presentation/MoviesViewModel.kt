package com.paymob.movies.modules.listing.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paymob.movies.db.api.ApiResult
import com.paymob.movies.db.local.datastore.DataStoreManager
import com.paymob.movies.modules.listing.data.params.MoviesListingParams
import com.paymob.movies.modules.listing.domain.entites.MovieEntity
import com.paymob.movies.modules.listing.domain.entites.MoviesListingEntity
import com.paymob.movies.modules.listing.domain.usecases.MoviesListingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesListingUseCase: MoviesListingUseCase,
    private val dataStoreManager: DataStoreManager,
) : ViewModel()
{
    private val _moviesListingState = MutableStateFlow<MoviesListingState>(
        MoviesListingState.Loading(
            false
        )
    )
    val moviesListingState: StateFlow<MoviesListingState> = _moviesListingState

    private var moviesParams : MoviesListingParams = MoviesListingParams()

    private var moviesListingEntity: MoviesListingEntity? = null

    init {
        fetchMovies()
    }

     fun fetchMovies() {
        viewModelScope.launch {
            when (val moviesResult = moviesListingUseCase(moviesParams)) {
                is ApiResult.Success -> {
                    moviesListingEntity = moviesResult.data
                    updateMoviesList()
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

    private fun getWishListIds() : List<String> {
        return dataStoreManager.getWishlistList()
    }

    fun updateMoviesList() {
     val wishlistIds : List<String> = getWishListIds()
        runBlocking {
            moviesListingEntity?.movies?.map { item ->
                val isExist : Boolean = wishlistIds.contains(item.id.toString())
                item.isFavorite = isExist
            }
        }
      _moviesListingState.value =
            MoviesListingState.Success(moviesListingEntity?.movies as ArrayList<MovieEntity>)
    }


    fun toggleFavoriteStatus(movie: MovieEntity) {
        _moviesListingState.value = MoviesListingState.Loading(true)

        val updatedWishlistIds = getWishListIds().toMutableList()

        if (!movie.isFavorite) {
            dataStoreManager.addMovieToWishlist(movie.id.toString(), viewModelScope) { success ->
                if (!success) {
                    _moviesListingState.value = MoviesListingState.WishlistError("Movie is already in the wishlist")
                } else {
                    updatedWishlistIds.add(movie.id.toString())
                    movie.isFavorite = true
                    updateMoviesListWithIds(updatedWishlistIds)
                }
            }
        } else {
            dataStoreManager.removeMovieFromWishlist(movie.id.toString(), viewModelScope) { success ->
                if (!success) {
                    _moviesListingState.value = MoviesListingState.WishlistError("Movie was not in the wishlist")
                } else {
                    updatedWishlistIds.remove(movie.id.toString())
                    movie.isFavorite = false
                    updateMoviesListWithIds(updatedWishlistIds)
                }
            }
        }
        _moviesListingState.value = MoviesListingState.Loading(false)
    }

    private fun updateMoviesListWithIds(wishlistIds: List<String>) {
        moviesListingEntity?.movies?.map { item ->
            item.isFavorite = wishlistIds.contains(item.id.toString())
        }
        _moviesListingState.value = MoviesListingState.Success(moviesListingEntity?.movies as ArrayList<MovieEntity>)
    }
}