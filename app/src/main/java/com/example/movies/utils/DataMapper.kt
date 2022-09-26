package com.example.movies.utils

import com.example.movies.data.source.local.entity.MoviesEntity
import com.example.movies.data.source.remote.response.MoviesItem
import com.example.movies.data.source.remote.response.MoviesResponse
import com.example.movies.domain.model.Movies


object DataMapper {
    fun mapResponsesToEntities(input: List<MoviesItem>): List<MoviesEntity> {
        val moviesList = ArrayList<MoviesEntity>()
        input.map {
            val movies = MoviesEntity(

                overview = it.overview,
                originalLanguage = it.originalLanguage,
                title = it.title,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                popularity = it.popularity,
                id = it.id,
            )
            moviesList.add(movies)
        }
        return moviesList
    }

    fun mapEntitiesToDomain(input: List<MoviesEntity>): List<Movies> =
        input.map {
            Movies(
                overview = it.overview,
                originalLanguage = it.originalLanguage,
                title = it.title,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                popularity = it.popularity,
                id = it.id,
            )
        }






}