package org.example.project.sdk

import org.example.project.cache.Database
import org.example.project.cache.DatabaseDriverFactory
import org.example.project.entity.RocketLaunch
import org.example.project.network.SpaceXApi

class SpaceXSDK(val databaseDriverFactory: DatabaseDriverFactory, val api: SpaceXApi) {
    private val database = Database(databaseDriverFactory)

    @Throws(Exception::class)
    suspend fun getLaunches(forceReload: Boolean): List<RocketLaunch> {
        val cachedLaunches = database.getAllLaunches()
        return if (cachedLaunches.isNotEmpty() && !forceReload) {
            cachedLaunches
        } else {
            api.getAllLaunches().also {
                database.clearAndCreateLaunches(it)
            }
        }
    }
}