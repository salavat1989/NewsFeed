package com.prod.newsfeed.di.module

import com.prod.newsfeed.data.NewsFeedRepositoryImpl
import com.prod.newsfeed.di.annotation.ApplicationScope
import com.prod.newsfeed.domain.NewsFeedRepository
import dagger.Binds
import dagger.Module

/**
 * Created by Kadyrov Salavat on 21.03.2022
 */
@Module
interface DomainModule {
	@ApplicationScope
	@Binds
	fun bindRepository(impl: NewsFeedRepositoryImpl): NewsFeedRepository
}