package com.prod.newsfeed.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.prod.newsfeed.NewsFeedApp
import com.prod.newsfeed.databinding.FragmentSearchBinding
import com.prod.newsfeed.ui.MainActivity
import com.prod.newsfeed.ui.ViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

/**
 * Created by Kadyrov Salavat on 22.03.2022
 */

class SearchFragment : Fragment() {
	@Inject
	lateinit var viewModelFactory: ViewModelFactory

	private val viewModel: SearchFragmentViewModel by lazy {
		ViewModelProvider(this, viewModelFactory)[SearchFragmentViewModel::class.java]
	}

	private val component by lazy {
		(requireActivity().application as NewsFeedApp).component
	}

	private val pagingDataAdapter by lazy(LazyThreadSafetyMode.NONE) {
		SearchFragmentPagingDataAdapter(requireActivity())
	}

	private var _binding: FragmentSearchBinding? = null
	private val binding
		get() = _binding ?: throw RuntimeException("FragmentSearchBinding is null")

	override fun onAttach(context: Context) {
		component.inject(this)
		super.onAttach(context)
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View? {
		_binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		setRvAdapter()
		setButtonListener()
		super.onViewCreated(view, savedInstanceState)
	}

	private fun setRvAdapter() {
		binding.rvNewsList.adapter = pagingDataAdapter
		pagingDataAdapter.onClick = {
//			val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
//			startActivity(intent)
			val action = SearchFragmentDirections.actionSearchFragmentToBrowserActivity(it)
			(requireActivity() as MainActivity).navController.navigate(action)
		}
		viewLifecycleOwner.lifecycleScope.launchWhenStarted {
			viewModel.listNews.collectLatest {
				pagingDataAdapter.submitData(it)
			}
		}
		pagingDataAdapter.addLoadStateListener { state ->
			with(binding) {
				rvNewsList.isVisible = state.refresh != LoadState.Loading
				progressBar.isVisible = state.refresh == LoadState.Loading
			}
		}
	}

	private fun setButtonListener() {
		binding.buttonSearch.setOnClickListener {
			viewModel.setQuery(binding.etSearch.text?.toString() ?: "")
		}
	}

	override fun onDestroyView() {
		_binding = null
		super.onDestroyView()
	}
}