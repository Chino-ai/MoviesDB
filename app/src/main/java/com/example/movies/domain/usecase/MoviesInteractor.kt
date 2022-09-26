package com.example.movies.domain.usecase

import com.example.movies.domain.repository.IMoviesRepository

class MoviesInteractor(private val moviesRepository: IMoviesRepository): MoviesUseCase {

    override fun getAllMovies() = moviesRepository.getAllMovies()



}