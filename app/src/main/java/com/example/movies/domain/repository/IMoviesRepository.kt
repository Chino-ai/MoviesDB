package com.example.movies.domain.repository

import com.example.movies.data.source.Resource
import com.example.movies.domain.model.Movies
import kotlinx.coroutines.flow.Flow

interface IMoviesRepository {

    fun getAllMovies(): Flow<Resource<List<Movies>>>



}