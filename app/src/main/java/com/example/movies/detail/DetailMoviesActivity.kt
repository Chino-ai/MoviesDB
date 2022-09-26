package com.example.movies.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.movies.databinding.ActivityDetailMoviesBinding
import com.example.movies.domain.model.Movies

class DetailMoviesActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra_data"
    }
    private lateinit var detailMoviesBinding: ActivityDetailMoviesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailMoviesBinding = ActivityDetailMoviesBinding.inflate(layoutInflater)
        setContentView(detailMoviesBinding.root)

        val detailMovies = intent.getParcelableExtra<Movies>(EXTRA_DATA)
        showDetailMember(detailMovies)
    }

    private fun showDetailMember(detailMovies: Movies?) {
        detailMovies?.let {
            detailMoviesBinding.judul.text =  detailMovies.title
            detailMoviesBinding.overview.text = detailMovies.overview
            detailMoviesBinding.bahasa.text = detailMovies.originalLanguage
            detailMoviesBinding.popularitas.text = detailMovies.popularity.toString()
            detailMoviesBinding.release.text = detailMovies.releaseDate
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500/"+detailMovies.posterPath)
                .into(detailMoviesBinding.poster)


        }
    }
}