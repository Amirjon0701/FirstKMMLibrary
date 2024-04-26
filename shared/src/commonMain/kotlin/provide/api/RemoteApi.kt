package provide.api

import data.model.Article
import data.repository.ArticleRepository

class RemoteApi(private val articleRepository: ArticleRepository) {
    suspend fun fetchArticles(): List<Article>{
        return articleRepository.getArticles()
    }
}