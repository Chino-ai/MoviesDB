package com.example.movies.data.source.remote

import android.util.Log
import com.example.animelover.core.data.source.remote.network.ApiResponse
import com.example.animelover.core.data.source.remote.network.ApiService
import com.example.movies.data.source.remote.response.MoviesItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getListMovies(): Flow<ApiResponse<List<MoviesItem>>> {
       return flow{
           try{
               val response  =apiService.getListMovies()
               val dataArray = response.results
               if (dataArray.isNotEmpty()){
                   emit(ApiResponse.Success(response.results))
               }else{
                   emit(ApiResponse.Empty)
               }
           }catch (e: Exception){
               emit(ApiResponse.Error(e.toString()))
               Log.e("RemoteDataSource",e.toString())
           }
       }.flowOn(Dispatchers.IO)

    }




}

