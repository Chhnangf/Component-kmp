package org.example.project.cache

import org.example.project.data.ImageEntity

class Database(databaseDriverFactory: DatabaseDriverFactory) {

    /**
     *  利用传递进来的数据库驱动工厂创建 SqlDriver 实例，
     *  然后使用这个驱动实例化 AppDatabase，它是 SQLDelight 生成的数据库访问类，
     *  用于执行数据库的查询、插入、更新和删除等操作。
     */
    private val database = ImageDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.imageDatabaseQueries

    internal fun insertImage(imageData: ByteArray) {
        dbQuery.insertImage(imageData)
    }

    internal fun getAllImages(): List<ImageEntity> {
        return dbQuery.selectAllImages().executeAsList().map {
            ImageEntity(
                id = it.id,
                data = it.data_,
                createdAt = it.created_at
            )
        }
    }

}