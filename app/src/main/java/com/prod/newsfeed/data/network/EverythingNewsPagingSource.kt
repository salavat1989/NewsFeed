package com.prod.newsfeed.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.prod.newsfeed.data.mapper.NewsMapper
import com.prod.newsfeed.domain.model.News
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import retrofit2.HttpException

/**
 * Created by Kadyrov Salavat on 22.03.2022
 */

class EverythingNewsPagingSource @AssistedInject constructor(
	private val apiService: ApiService,
	private val mapper: NewsMapper,
	@Assisted("query") private val query: String,
) : PagingSource<Int, News>() {
	override fun getRefreshKey(state: PagingState<Int, News>): Int? {
		val anchorPosition = state.anchorPosition ?: return null
		val page = state.closestPageToPosition(anchorPosition) ?: return null
		return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
	}

	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, News> {
		if (query.isEmpty()) {
			return LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
		}
		val page: Int = params.key ?: 1
		val pageSize: Int = params.loadSize.coerceAtMost(ApiService.DEFAULT_PAGE_SIZE)
		val response = try {
			apiService.getEverythingNews(query, page, pageSize)
		} catch (e: Exception) {
			return LoadResult.Error(e)
		}
		return if (response.isSuccessful) {
			val articlesDto = checkNotNull(response.body()).articles ?: emptyList()
			val news = mapper.mapListArticleDtoToListNews(articlesDto)
			val nextKey = if (news.size < pageSize) null else page + 1
			val prevKey = if (page == 1) null else page + 1
			LoadResult.Page(news, prevKey, nextKey)
		} else {
			LoadResult.Error(HttpException(response))
		}
	}

	@AssistedFactory
	interface Factory {
		fun create(@Assisted("query") query: String): EverythingNewsPagingSource
	}
}