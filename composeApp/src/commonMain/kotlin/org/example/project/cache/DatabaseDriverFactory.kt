package org.example.project.cache

import app.cash.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory() {
    // 定义一个方法用于创建并返回一个 SqlDriver 实例。
    // SqlDriver 是 SQLDelight 提供的用于与 SQLite 数据库交互的核心组件。
    fun createDriver(): SqlDriver
}