package com.example.movies.data.source

import com.dicoding.tourismapp.core.utils.AppExecutors
import com.example.movies.data.source.local.room.LocalDataSource
import com.example.animelover.core.data.source.remote.network.ApiResponse
import com.example.movies.data.source.remote.RemoteDataSource
import com.example.movies.data.source.remote.response.MoviesItem
import com.example.movies.domain.model.Movies
import com.example.movies.domain.repository.IMoviesRepository
import com.example.movies.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MoviesRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IMoviesRepository {

    override fun getAllMovies(): Flow<Resource<List<Movies>>> =
        object : NetworkBoundResource<List<Movies>, List<MoviesItem>>() {
            override fun loadFromDB(): Flow<List<Movies>> {
                return localDataSource.getAllMovies().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movies>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MoviesItem>>> =
                remoteDataSource.getListMovies()

            override suspend fun saveCallResult(data: List<MoviesItem>) {
                val moviesList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovies(moviesList)

            }
        }.asFlow()




}

