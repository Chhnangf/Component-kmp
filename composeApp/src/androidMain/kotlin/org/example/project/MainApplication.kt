package org.example.project

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize Koin here
//        startKoin {
//            androidContext(this@MainApplication)
//            modules(appModule)
//
//        }
    }
}