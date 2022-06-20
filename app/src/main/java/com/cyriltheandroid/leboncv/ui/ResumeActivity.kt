package com.cyriltheandroid.leboncv.ui

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.cyriltheandroid.leboncv.databinding.ActivityResumeBinding

const val resumePdfUrl =
    "https://www.fichier-pdf.fr/2022/06/16/cvleboncoincyrilpinalopes/cvleboncoincyrilpinalopes.pdf"

class ResumeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResumeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResumeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadResumePdfFromUrl(binding.resumeWebView)
    }

    private fun loadResumePdfFromUrl(webView: WebView) {
        webView.webViewClient = WebViewClient()
        webView.settings.setSupportZoom(true)
        val urlToLoad = "https://docs.google.com/gview?embedded=true&url=$resumePdfUrl"
        webView.loadUrl(urlToLoad)
    }
}