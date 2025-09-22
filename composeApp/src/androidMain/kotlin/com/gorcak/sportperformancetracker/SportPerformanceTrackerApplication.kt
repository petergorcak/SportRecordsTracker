package com.gorcak.sportperformancetracker

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.initialize
import com.gorcak.sportperformancetracker.di.initKoin
import org.koin.android.ext.koin.androidContext

class SportPerformanceTrackerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Firebase.initialize(this)
        initKoin{
            androidContext(this@SportPerformanceTrackerApplication)
        }
    }
}