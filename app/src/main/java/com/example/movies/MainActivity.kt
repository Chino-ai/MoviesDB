package com.example.movies

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.movies.databinding.ActivityMainBinding
import com.example.movies.list.MoviesActivity

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@MainActivity, MoviesActivity::class.java)
            startActivity(intent)
            finish()
        }, delay)
    }

    companion object {
        private const val delay = 2000L
    }
}
