package com.example.movies.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movies.data.source.local.entity.MoviesEntity

@Database(entities = [MoviesEntity::class], version = 4, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun movieDao(): MoviesDao

}