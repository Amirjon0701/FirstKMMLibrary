package db

import data.model.Source


internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = ArticleDB(databaseDriverFactory.createDriver())
    private val dbQuery = database.articleDBQueries

    internal fun getArticles(): List<data.model.Article>{
        return dbQuery.getArticles().executeAsList().map(::mapArticleDBToArticleApi)
    }

    private fun mapArticleDBToArticleApi(article: Article): data.model.Article{
        return data.model.Article(
            Source(article.source_id, article.source_name),
            article.author,
            article.title,
            article.description,
            article.url,
            article.url_to_image,
            article.published_at,
            article.content
        )
    }

    internal fun insertArticle(article: data.model.Article){
        dbQuery.insertArtcile(
            article.author,
            article.title,
            article.description,
            article.url,
            article.urlToImage,
            article.content,
            article.publishedAt,
            article.source.id,
            article.source.name
        )
    }
}