package com.example.movies.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.ProfileActivity
import com.example.movies.R
import com.example.movies.data.source.Resource
import com.example.movies.databinding.ActivityMoviesBinding
import com.example.movies.detail.DetailMoviesActivity
import com.example.movies.detail.DetailMoviesActivity.Companion.EXTRA_DATA
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesActivity : AppCompatActivity() {
    private lateinit var moviesBinding: ActivityMoviesBinding
    private lateinit var recycler: RecyclerView
    private val moviesViewModel : MoviesViewModel by viewModel()
    private val delay = 2000L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moviesBinding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(moviesBinding.root)

        val listMoviesAdapter = ListMoviesAdapter()
        listMoviesAdapter.onItemClick = { selectedData ->
            val intent = Intent(this@MoviesActivity, DetailMoviesActivity::class.java)
            intent.putExtra(EXTRA_DATA, selectedData)
            startActivity(intent)
        }


        moviesViewModel.movies.observe(this) { movies ->
            if(movies !=null){
              when(movies){
                  is Resource.Loading -> moviesBinding.progressBar.visibility = View.VISIBLE
                  is Resource.Success ->{
                      moviesBinding.progressBar.visibility = View.GONE
                      listMoviesAdapter.setData(movies.data)
                  }
                  is Resource.Error -> {
                      moviesBinding.progressBar.visibility = View.GONE
                         moviesBinding.error.visibility = View.VISIBLE
                  }

                  else -> {

                  }
              }
            }

        }

        moviesBinding.swipe.setOnRefreshListener {

            Handler(Looper.getMainLooper()).postDelayed({
                moviesBinding.swipe.isRefreshing = false
                finish()
                startActivity(intent)
                finish()
            }, delay)

        }


        with(moviesBinding.rv) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = listMoviesAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_profile -> {
                val intent = Intent(this@MoviesActivity, ProfileActivity::class.java)
                startActivity(intent)
            }
            R.id.action_grid_layout -> {
                recycler = findViewById(R.id.rv)
                recycler.layoutManager = GridLayoutManager(this,2)

            }
            R.id.action_linear_layout-> {
                recycler = findViewById(R.id.rv)
                recycler.layoutManager = LinearLayoutManager(this)
            }

            }
        return super.onOptionsItemSelected(item)
        }

}