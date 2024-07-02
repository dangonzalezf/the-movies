package com.example.themoviedbapp

import android.app.Application
import androidx.room.Room
import com.example.themoviedbapp.data.database.MoviesDataBase

class App : Application() {

    lateinit var db: MoviesDataBase
        private set

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(this, MoviesDataBase::class.java, "movies-db").build()
    }
}
