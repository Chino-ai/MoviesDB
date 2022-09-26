package com.example.movies.data.source.local.room

import com.example.movies.data.source.local.entity.MoviesEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val moviesDao: MoviesDao) {


    fun getAllMovies(): Flow<List<MoviesEntity>> = moviesDao.getAllMovies()


    suspend fun insertMovies(moviesList: List<MoviesEntity>)= moviesDao.insertMovies(moviesList)




}