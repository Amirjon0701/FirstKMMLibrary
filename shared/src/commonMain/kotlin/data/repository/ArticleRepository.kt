package data.repository

import data.model.Article

interface ArticleRepository {
    suspend fun getArticles(): List<Article>
}