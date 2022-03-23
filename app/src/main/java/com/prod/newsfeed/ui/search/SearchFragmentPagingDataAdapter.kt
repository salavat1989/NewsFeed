package com.prod.newsfeed.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.prod.newsfeed.databinding.ItemNewsBinding
import com.prod.newsfeed.domain.model.News
import com.prod.newsfeed.ui.main.NewsDiffUtil
import com.prod.newsfeed.ui.main.NewsViewHolder
import com.squareup.picasso.Picasso

/**
 * Created by Kadyrov Salavat on 22.03.2022
 */

class SearchFragmentPagingDataAdapter(context: Context) :
	PagingDataAdapter<News, NewsViewHolder>(NewsDiffUtil()) {
	var onClick: ((String) -> Unit)? = null

	private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
		val binding = ItemNewsBinding.inflate(
			layoutInflater,
			parent,
			false
		)
		return NewsViewHolder(binding)
	}

	override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
		val item = getItem(position)
		if (item != null) {
			with(holder.binding) {
				tvTitle.text = item.title
				tvDescription.text = item.description
				if (item.description.isNullOrEmpty()) tvDescription.visibility = View.GONE
				else tvDescription.visibility = View.VISIBLE
				tvSourceName.text = item.sourceName
				tvUpdateTime.text = item.publishedAt
				Picasso.get().load(item.urlToImage).into(ivImage)
				if (item.urlToImage.isNullOrEmpty()) ivImage.visibility = View.GONE
				else ivImage.visibility = View.VISIBLE
				root.setOnClickListener {
					onClick?.invoke(item.url)
				}
			}
		}
	}
}
