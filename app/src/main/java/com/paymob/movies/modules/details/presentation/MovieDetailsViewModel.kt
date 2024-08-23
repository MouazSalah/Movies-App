package com.paymob.movies.modules.details.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor() : ViewModel()
{
    private val _movieDetailsState = MutableStateFlow<MovieDetailsState>(
        MovieDetailsState.Loading(
            false
        )
    )
    val movieDetailsState: StateFlow<MovieDetailsState> = _movieDetailsState
}