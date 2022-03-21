package com.prod.newsfeed.data.network.model


import com.google.gson.annotations.SerializedName

data class ArticleDto(
    @SerializedName("author")
    val author: String?, // null
    @SerializedName("content")
    val content: String?, // null
    @SerializedName("description")
    val description: String, // На юге Китая потерпел крушение пассажирский Boeing 737 авиакомпании China Eastern
    @SerializedName("publishedAt")
    val publishedAt: String, // 2022-03-21T08:08:25Z
    @SerializedName("source")
    val source: SourceDto,
    @SerializedName("title")
    val title: String, // В Китае разбился пассажирский Boeing 737 - Московский Комсомолец
    @SerializedName("url")
    val url: String, // https://www.mk.ru/incident/2022/03/21/v-kitae-razbilsya-passazhirskiy-boeing-737.html
    @SerializedName("urlToImage")
    val urlToImage: String, // https://static.mk.ru/upload/entities/2022/03/21/11/articles/facebookPicture/0c/ea/1a/88/e479a08f1d2ed4ffde642830e503ddae.jpg
)