package org.example.project.cache

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {

    /**
     *  利用传递进来的数据库驱动工厂创建 SqlDriver 实例，
     *  然后使用这个驱动实例化 AppDatabase，它是 SQLDelight 生成的数据库访问类，
     *  用于执行数据库的查询、插入、更新和删除等操作。
     */
    private val database = ImageDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.imageDatabaseQueries

    // 存储图片
//    suspend fun storeImage(imageData: ByteArray): Long {
//        return dbQuery.insertImage(imageData).executeAsInsert().run { this.id }
//    }
//
//    // 根据ID查询图片
//    suspend fun getImageById(id: Long): ByteArray? {
//        return dbQuery.getImageById(id).executeAsOneOrNull()?.data
//    }
//
//    // 更新图片数据
//    suspend fun updateImage(id: Long, updatedImageData: ByteArray) {
//        dbQuery.updateImage(id, updatedImageData).executeUpdateDelete()
//    }
//
//    // 删除图片
//    suspend fun deleteImage(id: Long) {
//        dbQuery.deleteImage(id).executeUpdateDelete()
//    }

}