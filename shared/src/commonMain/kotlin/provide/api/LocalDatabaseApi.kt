package provide.api

import data.model.Article
import db.Database
import db.DatabaseDriverFactory

class LocalDatabaseApi(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = Database(databaseDriverFactory)

    suspend fun getArticles(): List<Article>{
        return database.getArticles()
    }

    suspend fun insertArticle(article: Article){
        database.insertArticle(article)
    }
}