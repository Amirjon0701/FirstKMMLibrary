package db

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.koin.java.KoinJavaComponent.inject

class AndroidDatabaseDriverFactory: DatabaseDriverFactory {
    private val context: Context by inject(Context::class.java)

    override fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(ArticleDB.Schema, context = context, "articledb.db")
    }
}

actual fun provideDatabaseDriverFactory(): DatabaseDriverFactory {
    return AndroidDatabaseDriverFactory()
}