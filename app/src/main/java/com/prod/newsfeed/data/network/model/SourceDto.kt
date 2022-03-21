package com.prod.newsfeed.data.network.model


import com.google.gson.annotations.SerializedName

data class SourceDto(
    @SerializedName("id")
    val id: String?, // null
    @SerializedName("name")
    val name: String, // Www.mk.ru
)