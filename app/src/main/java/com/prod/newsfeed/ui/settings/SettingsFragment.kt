package com.prod.newsfeed.ui.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.radiobutton.MaterialRadioButton
import com.prod.newsfeed.NewsFeedApp
import com.prod.newsfeed.databinding.FragmentSettingsBinding
import com.prod.newsfeed.ui.ViewModelFactory
import javax.inject.Inject

/**
 * Created by Kadyrov Salavat on 22.03.2022
 */

class SettingsFragment : Fragment() {
	@Inject
	lateinit var viewModelFactory: ViewModelFactory

	private val viewModel: SettingsFragmentViewModel by lazy {
		ViewModelProvider(this, viewModelFactory)[SettingsFragmentViewModel::class.java]
	}

	private val component by lazy {
		(requireActivity().application as NewsFeedApp).component
	}

	private var _binding: FragmentSettingsBinding? = null
	private val binding
		get() = _binding ?: throw RuntimeException("FragmentSettingsBinding is null")

	private var isRadioCreated = false

	override fun onAttach(context: Context) {
		component.inject(this)
		super.onAttach(context)
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View? {
		_binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		setViewModelObserver()
		super.onViewCreated(view, savedInstanceState)
	}

	private fun setViewModelObserver() {
		viewModel.radioContent.observe(viewLifecycleOwner) {
			if (!isRadioCreated) {
				for (code in it) {
					val radio = MaterialRadioButton(requireActivity())
					radio.text = code.key
					radio.setOnCheckedChangeListener {
							_,
							b,
						->
						if (b) viewModel.radioChecked(code.key)
					}
					binding.rgCountryCode.addView(radio)
					if (code.value)
						radio.isChecked = code.value
				}
				isRadioCreated = true
			}
		}
	}
}