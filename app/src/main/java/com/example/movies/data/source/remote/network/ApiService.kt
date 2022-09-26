package com.example.animelover.core.data.source.remote.network




import com.example.movies.data.source.remote.response.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing")
    suspend fun getListMovies(
        @Query("api_key") apiKey: String = "fffea778fe0c659e80f4fa3d934f6d16"
    ): MoviesResponse




}