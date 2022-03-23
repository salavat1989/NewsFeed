package com.prod.newsfeed.data.mapper

import android.app.Application
import com.prod.newsfeed.data.database.model.NewsDb
import com.prod.newsfeed.data.network.model.ArticleDto
import com.prod.newsfeed.domain.model.News
import kotlinx.datetime.Instant
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Created by Kadyrov Salavat on 21.03.2022
 */

class NewsMapper @Inject constructor(
	private val application: Application,
) {
	fun mapListArticleDtoToListNewsDb(dto: List<ArticleDto>): List<NewsDb> {
		return dto.map {
			mapArticleDtoToNewsDb(it)
		}
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

	fun mapListNewsDbToListNews(db: List<NewsDb>): List<News> = db.map { mapNewsDbToNews(it) }

	private fun mapNewsDbToNews(db: NewsDb): News {
		return News(
			sourceName = db.sourceName,
			title = db.title,
			description = db.description,
			url = db.url,
			urlToImage = db.urlToImage,
			publishedAt = convertUnixTimeToString(db.publishedAt, TIME_TEMPLATE)
		)
	}

	private val locale = application.resources.configuration.locales[0]
	private fun convertUnixTimeToString(t: Long, template: String): String {
		val dateFormat = SimpleDateFormat(template, locale)
		val date = Date(t)
		return dateFormat.format(date)
	}

	fun mapListArticleDtoToListNews(dto: List<ArticleDto>): List<News> {
		return dto.map {
			mapArticleDtoToNews(it)
		}
	}


	private fun mapArticleDtoToNews(dto: ArticleDto): News {
		return News(
			sourceName = dto.source.name,
			title = dto.title,
			description = dto.description,
			url = dto.url,
			urlToImage = dto.urlToImage,
			publishedAt = convertUnixTimeToString(
				Instant.parse(dto.publishedAt).toEpochMilliseconds(),
				TIME_TEMPLATE
			)
		)
	}

	companion object {
		private const val TIME_TEMPLATE = "dd MMMM y H:mm"
	}
}