package com.prod.newsfeed.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.prod.newsfeed.R

class MainActivity : AppCompatActivity() {
	private val navController: NavController by lazy {
		val navHostFragment = supportFragmentManager
			.findFragmentById(R.id.mainFragmentContainer) as NavHostFragment
		navHostFragment.navController
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		setBottomNavigationController()
	}

	private fun setBottomNavigationController() {
		findViewById<BottomNavigationView>(R.id.bottom_navigation).setupWithNavController(
			navController)
	}

	override fun onSupportNavigateUp() = navController.navigateUp()
}