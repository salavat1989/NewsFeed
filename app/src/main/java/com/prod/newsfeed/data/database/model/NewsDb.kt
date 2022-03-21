package com.prod.newsfeed.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Kadyrov Salavat on 21.03.2022
 */
@Entity(tableName = "news")
data class NewsDb(
	@PrimaryKey(autoGenerate = true)
	val id: Long,
	val sourceName: String,
	val author: String?,
	val title: String,
	val description: String,
	val url: String,
	val urlToImage: String,
	val publishedAt: Long,
)