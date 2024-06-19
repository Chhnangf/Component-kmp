//package org.example.project
//
//import org.example.project.cache.AndroidDatabaseDriverFactory
//import org.example.project.network.SpaceXApi
//import org.example.project.sdk.SpaceXSDK
//import org.koin.android.ext.koin.androidContext
//import org.koin.androidx.viewmodel.dsl.viewModel
//import org.koin.dsl.module
//
//val appModule = module {
//    single<SpaceXApi> { SpaceXApi() }
//    single<SpaceXSDK> {
//        SpaceXSDK(
//            databaseDriverFactory = AndroidDatabaseDriverFactory(androidContext()),
//            api = get()
//        )
//    }
//
//    viewModel {RocketLaunchViewModel(sdk = get())}
//}