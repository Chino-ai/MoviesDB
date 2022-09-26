package com.example.movies.di

import com.example.movies.domain.usecase.MoviesUseCase
import com.example.movies.domain.usecase.MoviesInteractor
import com.example.movies.list.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MoviesUseCase> { MoviesInteractor(get()) }

}

val viewModelModule = module {
    viewModel { MoviesViewModel(get()) }


}
