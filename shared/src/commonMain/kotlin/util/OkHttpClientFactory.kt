@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package util

import io.ktor.client.HttpClient


expect class OkHttpClientFactory() {
    fun createOkHttpClient(): HttpClient
}