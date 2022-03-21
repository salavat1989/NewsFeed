package com.prod.newsfeed.data.mapper

import com.prod.newsfeed.data.database.model.NewsDb
import com.prod.newsfeed.data.network.model.ArticleDto
import com.prod.newsfeed.data.network.model.NewsContainerDto
import kotlinx.datetime.Instant
import javax.inject.Inject

/**
 * Created by Kadyrov Salavat on 21.03.2022
 */

class NewsMapper @Inject constructor(

) {
	fun mapNewsContainerDtoToListNewsDb(dto: NewsContainerDto): List<NewsDb> {
		return dto.articles?.map {
			mapArticleDtoToNewsDb(it)
		} ?: emptyList()
	}

	private fun mapArticleDtoToNewsDb(dto: ArticleDto): NewsDb {
		return NewsDb(
			id = 0,
			sourceName = dto.source.name,
			author = dto.author,
			title = dto.title,
			description = dto.description,
			url = dto.url,
			urlToImage = dto.urlToImage,
			publishedAt = Instant.parse(dto.publishedAt).toEpochMilliseconds()
		)
	}
}