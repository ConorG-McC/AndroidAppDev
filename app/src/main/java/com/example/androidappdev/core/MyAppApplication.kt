package com.example.androidappdev.core

import android.app.Application

class MyAppApplication : Application() {

    companion object {
        lateinit var container: AppDataContainer
    }

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}