package io.github.yusukeiwaki.bettertcard.shufoo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import io.github.yusukeiwaki.bettertcard.MainActivity

class ShufooLoginActivity : AppCompatActivity() {
    companion object {
        fun newIntent(context: Context) = Intent(context, ShufooLoginActivity::class.java)

        private const val LOGIN_URL = "https://tsite.jp/tm/pc/auth/STKIp1101000.do"
        private const val LOGIN_PARAMS = "net_id=2026&s=m6pJBtijhCUPpKKDvxscZQ==&s_back=3dlq8o7AZr8f/OOM3iKkUg=="
    }

    private lateinit var webView: ShufooWebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        webView = ShufooWebView(this)
        setContentView(webView)
        webView.tokenCallback = {
            MyPref(this).token = it
            NavUtils.navigateUpTo(this, MainActivity.newIntent(this))
        }
        webView.postUrl(LOGIN_URL, LOGIN_PARAMS.toByteArray())
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
