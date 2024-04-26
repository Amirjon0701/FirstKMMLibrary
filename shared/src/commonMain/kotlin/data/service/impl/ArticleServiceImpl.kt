package data.service.impl

import data.model.Article
import data.model.MetaData
import data.service.ArticleService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.HttpClientEngineBase
import io.ktor.client.request.get
import io.ktor.http.isSuccess
import io.ktor.util.logging.KtorSimpleLogger
import io.ktor.util.logging.Logger
import org.koin.core.logger.Level

class ArticleServiceImpl(private val okHttpClient: HttpClient): ArticleService {
    override suspend fun getArticles(): MetaData {
//        val response = okHttpClient.get("https://newsapi.org/v2/everything?q=all&apiKey=ca9984bf10144e429d80f30de1c8ab7e")
//        val response = okHttpClient.get("v2/everything?q=all&apiKey=ca9984bf10144e429d80f30de1c8ab7e")

        val response = okHttpClient.get("v2/everything?q=all")
        return if(response.status.isSuccess()){
            response.body()
        }
        else{
            throw Exception("Http request exception ${response.status.description}")
        }
    }
}