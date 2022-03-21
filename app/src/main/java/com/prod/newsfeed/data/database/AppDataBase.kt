package com.prod.newsfeed.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.prod.newsfeed.data.database.model.NewsDb

/**
 * Created by Kadyrov Salavat on 21.03.2022
 */
@Database(
	entities = [
		NewsDb::class
	],
	version = 1,
	exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
	companion object {
		private var db: AppDataBase? = null
		private const val DB_NAME = "main.db"
		private val LOCK = Any()
		fun getInstance(context: Context): AppDataBase {
			synchronized(LOCK) {
				db?.let {
					return it
				}
				val instance = Room.databaseBuilder(
					context,
					AppDataBase::class.java,
					DB_NAME
				).build()
				db = instance
				return instance
			}
		}
	}

	abstract fun newsFeedDao(): NewsFeedDao
}