package data.service

import data.model.MetaData

interface ArticleService {
    suspend fun getArticles(): MetaData
}