package com.prod.newsfeed.di.module

import android.app.Application
import com.prod.newsfeed.data.database.AppDataBase
import com.prod.newsfeed.data.database.NewsFeedDao
import com.prod.newsfeed.data.network.ApiFactory
import com.prod.newsfeed.data.network.ApiService
import com.prod.newsfeed.di.annotation.ApplicationScope
import dagger.Module
import dagger.Provides

/**
 * Created by Kadyrov Salavat on 21.03.2022
 */
@Module
interface DataModule {
	companion object {
		@ApplicationScope
		@Provides
		fun provideApiService(): ApiService {
			return ApiFactory.apiService
		}

		@ApplicationScope
		@Provides
		fun provideNewsFeedDao(application: Application): NewsFeedDao {
			return AppDataBase.getInstance(application).newsFeedDao()
		}
	}
}