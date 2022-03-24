package com.prod.newsfeed.ui

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.navigation.navArgs
import com.prod.newsfeed.R

/**
 * Created by Kadyrov Salavat on 24.03.2022
 */

class BrowserActivity : Activity() {

	private lateinit var webView: WebView

	private lateinit var progressBar: ProgressBar

	private val args by navArgs<BrowserActivityArgs>()
	override fun onCreate(savedInstanceState: Bundle?) {
		setContentView(R.layout.activity_browser)
		restoreStateOrLoad(savedInstanceState)
		super.onCreate(savedInstanceState)
	}

	override fun onRestoreInstanceState(savedInstanceState: Bundle) {
		webView.restoreState(savedInstanceState)
		super.onRestoreInstanceState(savedInstanceState)
	}

	override fun onSaveInstanceState(outState: Bundle) {
		super.onSaveInstanceState(outState)
		webView.saveState(outState)
	}

	private fun restoreStateOrLoad(savedInstanceState: Bundle?) {
		progressBar = findViewById(R.id.progressBar)
		webView = findViewById(R.id.webView)
		webView.webViewClient = object : WebViewClient() {
			override fun onPageFinished(view: WebView?, url: String?) {
				super.onPageFinished(view, url)
				progressBar.visibility = View.GONE
			}
		}
		webView.settings.javaScriptEnabled = true
		webView.settings.allowContentAccess = true
		webView.settings.domStorageEnabled = true
		webView.settings.useWideViewPort = true
		if (savedInstanceState == null)
			loadUrl()
	}

	private fun loadUrl() {
		webView.loadUrl(args.url)
	}

}