package org.example.project

import org.example.project.cache.Database
import org.example.project.cache.DatabaseDriverFactory

class ImageSDk(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = Database(databaseDriverFactory)

}

