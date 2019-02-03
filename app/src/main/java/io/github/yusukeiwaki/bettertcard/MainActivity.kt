package io.github.yusukeiwaki.bettertcard

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import io.github.yusukeiwaki.bettertcard.shufoo.MyPref
import io.github.yusukeiwaki.bettertcard.shufoo.ShufooLoginActivity
import io.github.yusukeiwaki.bettertcard.tcard.TCardActivity

class MainActivity : Activity() {
    companion object {
        fun newIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val token = MyPref(this).token
        if (token == null) {
            startActivity(ShufooLoginActivity.newIntent(this))
            finish()
            return
        }

        TcardTokenGetter.fetch(token, object : TcardTokenGetter.TokenCallback {
            override fun onValidToken(tCardToken: String) {
                startActivity(TCardActivity.newIntent(this@MainActivity, tCardToken))
                finish()
            }

            override fun onInvalidToken() {
                startActivity(ShufooLoginActivity.newIntent(this@MainActivity))
                finish()
            }

            override fun onFatalError(reason: String) {
                Toast.makeText(this@MainActivity, reason, Toast.LENGTH_SHORT).show()
                finish()
            }
        })
    }
}
