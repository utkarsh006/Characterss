package com.example.characters.presentation.character_detail.components

import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.characters.common.Constants.MEDIA_HEIGHT


@Composable
fun TrailerView(url: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(MEDIA_HEIGHT.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    settings.apply {
                        javaScriptEnabled = true
                        domStorageEnabled = true
                        mediaPlaybackRequiresUserGesture = false
                        loadWithOverviewMode = true
                        useWideViewPort = true
                    }
                    webViewClient = WebViewClient()
                    webChromeClient = WebChromeClient()
                }
            },
            update = { it.loadUrl(url) },
            modifier = Modifier.fillMaxSize()
        )
    }
}