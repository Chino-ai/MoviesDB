package com.example.movies.domain.usecase


import com.example.movies.data.source.Resource
import com.example.movies.domain.model.Movies
import kotlinx.coroutines.flow.Flow

interface MoviesUseCase {
    fun getAllMovies(): Flow<Resource<List<Movies>>>


}