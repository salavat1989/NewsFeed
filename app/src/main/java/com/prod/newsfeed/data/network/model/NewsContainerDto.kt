package com.prod.newsfeed.data.network.model


import com.google.gson.annotations.SerializedName

data class NewsContainerDto(
    @SerializedName("articles")
    val articles: List<ArticleDto>?,
    @SerializedName("status")
    val status: String, // ok
    @SerializedName("totalResults")
    val totalResults: Int?, // 30
)