package db

import app.cash.sqldelight.db.SqlDriver

interface DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}

expect fun provideDatabaseDriverFactory(): DatabaseDriverFactory
