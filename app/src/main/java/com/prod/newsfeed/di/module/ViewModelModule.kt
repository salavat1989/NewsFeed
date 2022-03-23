package com.prod.newsfeed.di.module

import androidx.lifecycle.ViewModel
import com.prod.newsfeed.di.annotation.ViewModelKey
import com.prod.newsfeed.ui.main.MainFragmentViewModel
import com.prod.newsfeed.ui.search.SearchFragmentViewModel
import com.prod.newsfeed.ui.settings.SettingsFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Kadyrov Salavat on 21.03.2022
 */
@Module
interface ViewModelModule {
	@IntoMap
	@ViewModelKey(MainFragmentViewModel::class)
	@Binds
	fun bindMainFragmentViewModel(impl: MainFragmentViewModel): ViewModel

	@IntoMap
	@ViewModelKey(SettingsFragmentViewModel::class)
	@Binds
	fun bindSettingsFragmentViewModel(impl: SettingsFragmentViewModel): ViewModel

	@IntoMap
	@ViewModelKey(SearchFragmentViewModel::class)
	@Binds
	fun bindSearchFragmentViewModel(impl: SearchFragmentViewModel): ViewModel
}