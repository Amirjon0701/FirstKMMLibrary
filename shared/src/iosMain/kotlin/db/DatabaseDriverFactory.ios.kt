package db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver

class IosDatabaseDriverFactory: DatabaseDriverFactory {
    override fun createDriver(): SqlDriver {
        return NativeSqliteDriver(ArticleDB.Schema, "articledb.db")
    }
}

actual fun provideDatabaseDriverFactory(): DatabaseDriverFactory {
    return IosDatabaseDriverFactory()
}