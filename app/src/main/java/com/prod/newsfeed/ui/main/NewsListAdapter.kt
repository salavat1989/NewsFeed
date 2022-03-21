package com.prod.newsfeed.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.prod.newsfeed.databinding.ItemNewsBinding
import com.prod.newsfeed.domain.model.News
import com.squareup.picasso.Picasso

/**
 * Created by Kadyrov Salavat on 21.03.2022
 */

class NewsListAdapter : ListAdapter<News, NewsViewHolder>(NewsDiffUtil()) {
	var onLongClick: ((String) -> Boolean)? = null
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
		val binding = ItemNewsBinding.inflate(
			LayoutInflater.from(parent.context),
			parent,
			false
		)
		return NewsViewHolder(binding)
	}

	override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
		val item = getItem(position)
		with(holder.binding) {
			tvTitle.text = item.title
			tvDescription.text = item.description
			tvSourceName.text = item.sourceName
			tvUpdateTime.text = item.publishedAt
			Picasso.get().load(item.urlToImage).into(ivImage)
			root.setOnLongClickListener {
				onLongClick?.invoke(item.url) ?: false
			}

		}
	}
}