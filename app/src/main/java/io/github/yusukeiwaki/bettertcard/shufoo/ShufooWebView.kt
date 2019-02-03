package io.github.yusukeiwaki.bettertcard.shufoo

import android.content.Context
import android.webkit.WebView
import android.webkit.WebViewClient
import java.net.URLDecoder

class ShufooWebView(context: Context) : WebView(context) {
    init {
        webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                return url?.let{ shouldOverrideUrl(it) } ?: super.shouldOverrideUrlLoading(view, url)
            }
        }
        settings.apply {
            javaScriptEnabled = true
        }
    }

    var tokenCallback: ((String) -> Unit)? = null

    private fun shouldOverrideUrl(url: String): Boolean? {
        if (url.startsWith("cccshufootop://")) {
            return handleCallbackUrl(url)
        } else {
            return null
        }
    }

    private fun handleCallbackUrl(url: String): Boolean {
        val index1 = url.indexOf("00_")
        if (index1 >= 0) {
            val token = URLDecoder.decode(url.substring(index1 + 3), "UTF-8")
            tokenCallback?.invoke(token)
            return true
        } else {
            return false
        }
    }
}
