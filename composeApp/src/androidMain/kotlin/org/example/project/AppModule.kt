package org.example.project

import org.example.project.cache.AndroidDatabaseDriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single<ImageSDK> {
        ImageSDK(
            databaseDriverFactory = AndroidDatabaseDriverFactory(androidContext())
        )
    }
}