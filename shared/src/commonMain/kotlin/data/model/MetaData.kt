package data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MetaData(
    @SerialName("status")
    val status: String,
    @SerialName("totalResults")
    val totalResults: Long,
    @SerialName("articles")
    val articles: List<Article>
)