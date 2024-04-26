package org.example.firstkmmproject

import App
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import data.model.Article
import data.model.Source
import data.repository.ArticleRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import provide.api.LocalDatabaseApi
import provide.api.RemoteApi

class MainActivity : ComponentActivity() {
    private val articleRepository: ArticleRepository by inject()
    private val remoteApi: RemoteApi by inject()
    private val localDatabaseApi: LocalDatabaseApi by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }

        CoroutineScope(Dispatchers.IO + SupervisorJob()).launch {
            val articles = articleRepository.getArticles()
            Log.i("TAG", articles.size.toString())
            val localArticles = localDatabaseApi.getArticles()
            Log.i("TAG", "local articles size ${localArticles.size}")
//            localDatabaseApi.insertArticle(Article(
//                    Source(null, "Yahoo Entertainment"),
//                    "Lawrence Bonk",
//                    "Slack rolls out its AI tools to all paying customers",
//                "Slack just rolled out its AI tools to all paying users, after releasing them to a select subset of customers earlier this year. The company’s been teasing these features since last year and, well, now they’re here.\nThe AI auto-generates channel recaps to give…",
//                "https://consent.yahoo.com/v2/collectConsent?sessionId=1_cc-session_5e2e3473-d16f-455b-9eb8-7e88d86868dd",
//                null,
//                "2024-04-18T12:00:45Z",
//                "If you click 'Accept all', we and our partners, including 238 who are part of the IAB Transparency &amp; Consent Framework, will also store and/or access information on a device (in other words, use … [+678 chars]"
//                )
//            )
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}