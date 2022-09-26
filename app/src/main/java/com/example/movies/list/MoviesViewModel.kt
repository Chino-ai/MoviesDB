package com.example.movies.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.movies.domain.usecase.MoviesUseCase


class MoviesViewModel(private val moviesUseCase: MoviesUseCase) : ViewModel() {

    val movies = moviesUseCase.getAllMovies().asLiveData()

}

