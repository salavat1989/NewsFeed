package com.prod.newsfeed.di

import android.app.Application
import com.prod.newsfeed.di.annotation.ApplicationScope
import com.prod.newsfeed.di.module.DataModule
import com.prod.newsfeed.di.module.DomainModule
import com.prod.newsfeed.di.module.ViewModelModule
import com.prod.newsfeed.ui.main.MainFragment
import com.prod.newsfeed.ui.search.SearchFragment
import com.prod.newsfeed.ui.settings.SettingsFragment
import dagger.BindsInstance
import dagger.Component

/**
 * Created by Kadyrov Salavat on 21.03.2022
 */
@ApplicationScope
@Component(modules = [
	DataModule::class,
	DomainModule::class,
	ViewModelModule::class
])
interface ApplicationComponent {

	fun inject(fragment: MainFragment)
	fun inject(fragment: SettingsFragment)
	fun inject(fragment: SearchFragment)

	@Component.Factory
	interface Factory {
		fun create(
			@BindsInstance application: Application,
		): ApplicationComponent
	}
}