package data.repository.impl

import data.model.Article
import data.repository.ArticleRepository
import data.service.ArticleService

class ArticleRepositoryImpl(private val articleService: ArticleService): ArticleRepository {
    override suspend fun getArticles(): List<Article> {
        return articleService.getArticles().articles
    }
}