package com.example.movies.di

import androidx.room.Room
import com.dicoding.tourismapp.core.utils.AppExecutors
import com.example.animelover.core.data.source.remote.network.ApiService
import com.example.movies.data.source.MoviesRepository
import com.example.movies.data.source.local.room.LocalDataSource
import com.example.movies.data.source.local.room.MoviesDatabase
import com.example.movies.data.source.remote.RemoteDataSource
import com.example.movies.domain.repository.IMoviesRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory {
        get<MoviesDatabase>().movieDao()
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            MoviesDatabase::class.java,
            "movies.db"
        ).fallbackToDestructiveMigration()
            .build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

        single {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(get())
                .build()
            retrofit.create(ApiService::class.java)
        }

}

val repositoryModule = module{
    single {
        LocalDataSource(get())
    }
    single{
        RemoteDataSource(get())
    }

    factory {
        AppExecutors()
    }
    single<IMoviesRepository>{
        MoviesRepository(get(),get(),get())
    }
}