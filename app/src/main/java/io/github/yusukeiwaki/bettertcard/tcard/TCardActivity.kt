package io.github.yusukeiwaki.bettertcard.tcard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.github.yusukeiwaki.bettertcard.BuildConfig

class TCardActivity : AppCompatActivity() {
    companion object {
        fun newIntent(context: Context, token: String) =
            Intent(context, TCardActivity::class.java).also { intent ->
                intent.putExtra("token", token)
            }

        private const val URL = "https://tsite.jp/tm/pc/mbt/STKIp2601001.do"
    }

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        webView = WebView(this)
        setContentView(webView)

        webView.apply {
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    if (url == null) return false

                    if (url.contains("busy")) {
                        Toast.makeText(this@TCardActivity, "もうちょっとまってから再度試してください", Toast.LENGTH_SHORT).show()
                        finish()
                        return true
                    }
                    return super.shouldOverrideUrlLoading(view, url)
                }
            }
            settings.apply {
                javaScriptEnabled = true
            }
        }

        val token = intent.getStringExtra("token")
        val url = "${URL}?app_id_tkn=${token}"
        val data = "TID_2STEP=${BuildConfig.TCARD_NUMBER}&BIRTHDAY=${BuildConfig.BIRTHDAY}&SENIMOTO_FLG=1&MOBASEN_KBN=0".toByteArray()
        webView.postUrl(url, data)
    }
}
