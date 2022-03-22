package com.prod.newsfeed.ui.main

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.prod.newsfeed.NewsFeedApp
import com.prod.newsfeed.databinding.FragmentMainBinding
import com.prod.newsfeed.ui.ViewModelFactory
import javax.inject.Inject


/**
 * Created by Kadyrov Salavat on 21.03.2022
 */

class MainFragment : Fragment() {
	@Inject
	lateinit var viewModelFactory: ViewModelFactory

	private val newsListAdapter: NewsListAdapter = NewsListAdapter()

	private val viewModel: MainFragmentViewModel by lazy {
		ViewModelProvider(this, viewModelFactory)[MainFragmentViewModel::class.java]
	}

	private val component by lazy {
		(requireActivity().application as NewsFeedApp).component
	}

	private var _binding: FragmentMainBinding? = null
	private val binding
		get() = _binding ?: throw RuntimeException("FragmentMainBinding is null")

	override fun onAttach(context: Context) {
		component.inject(this)
		super.onAttach(context)
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View? {
		_binding = FragmentMainBinding.inflate(layoutInflater, container, false)
		return binding.root
	}

	override fun onDestroyView() {
		_binding = null
		super.onDestroyView()
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		setRvAdapters()
		setViewModelObserver()
		setSrlListener()
		super.onViewCreated(view, savedInstanceState)
	}

	private fun setRvAdapters() {
		binding.rvNewsList.adapter = newsListAdapter
		newsListAdapter.onClick = {
			val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
			startActivity(intent)
		}
	}

	private fun setViewModelObserver() {
		viewModel.listNews.observe(viewLifecycleOwner) {
			newsListAdapter.submitList(it)
		}
		viewModel.refreshStatus.observe(viewLifecycleOwner) {
			binding.swipeRefreshLayout.isRefreshing = it
		}
	}

	private fun setSrlListener() {
		binding.swipeRefreshLayout.setOnRefreshListener {
			viewModel.refreshNews()
		}
	}
}