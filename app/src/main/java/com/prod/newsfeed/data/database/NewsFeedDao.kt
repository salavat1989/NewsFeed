package com.prod.newsfeed.data.database

import androidx.room.*
import com.prod.newsfeed.data.database.model.NewsDb

/**
 * Created by Kadyrov Salavat on 21.03.2022
 */
@Dao
interface NewsFeedDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertNews(db: List<NewsDb>)

	@Query("DELETE FROM news")
	suspend fun deleteAllNews()

	@Transaction
	suspend fun insertNewsAfterDelete(db: List<NewsDb>){
		deleteAllNews()
		insertNews(db)
	}
}