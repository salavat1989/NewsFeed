package com.prod.newsfeed.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.prod.newsfeed.NewsFeedApp
import com.prod.newsfeed.databinding.FragmentMainBinding

/**
 * Created by Kadyrov Salavat on 21.03.2022
 */

class MainFragment : Fragment() {
	private val component by lazy {
		(requireActivity().application as NewsFeedApp).component
	}
	private var _binding: FragmentMainBinding? = null
	val binding
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
}