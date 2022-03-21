package com.prod.newsfeed

import android.app.Application
import com.prod.newsfeed.di.DaggerApplicationComponent

/**
 * Created by Kadyrov Salavat on 21.03.2022
 */

class NewsFeedApp : Application() {
	val component by lazy {
		DaggerApplicationComponent.factory().create(this)
	}
}