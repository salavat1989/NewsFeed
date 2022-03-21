package com.prod.newsfeed.di

import android.app.Application
import com.prod.newsfeed.di.annotation.ApplicationScope
import com.prod.newsfeed.di.module.DataModule
import com.prod.newsfeed.di.module.DomainModule
import com.prod.newsfeed.ui.main.MainFragment
import dagger.BindsInstance
import dagger.Component

/**
 * Created by Kadyrov Salavat on 21.03.2022
 */
@ApplicationScope
@Component(modules = [
	DataModule::class,
	DomainModule::class,
])
interface ApplicationComponent {

	fun inject(fragment: MainFragment)

	@Component.Factory
	interface Factory {
		fun create(
			@BindsInstance application: Application,
		): ApplicationComponent
	}
}