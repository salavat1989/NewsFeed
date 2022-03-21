package com.prod.newsfeed.ui.main

import androidx.recyclerview.widget.DiffUtil
import com.prod.newsfeed.domain.model.News

/**
 * Created by Kadyrov Salavat on 21.03.2022
 */

class NewsDiffUtil : DiffUtil.ItemCallback<News>() {
	override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
		return oldItem.url == newItem.url
	}

	override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
		return oldItem == newItem
	}
}