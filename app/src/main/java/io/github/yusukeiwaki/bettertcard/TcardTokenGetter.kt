package io.github.yusukeiwaki.bettertcard

import com.github.kittinunf.fuel.httpGet
import java.net.URLEncoder

object TcardTokenGetter {
    interface TokenCallback {
        fun onValidToken(tCardToken: String)
        fun onInvalidToken()
        fun onFatalError(reason: String)
    }

    fun fetch(token: String, tokenCallback: TokenCallback) {
        val encodedToken = URLEncoder.encode(token, "UTF-8")
        val url = "http://www.shufoo.net/api/smartphone/getCccBcdToken.php?kaiinId=${encodedToken}"

        url.httpGet().responseString { handler ->
            handler.fold(success = { body ->
                val regex = "<data name=\"app_id_tkn\">(.*)</data>".toRegex()
                if (regex.containsMatchIn(body)) {
                    val tCatdToken = regex.find(body)!!.groupValues[1]
                    if (tCatdToken.isBlank()) {
                        tokenCallback.onInvalidToken()
                    } else {
                        tokenCallback.onValidToken(tCatdToken)
                    }
                } else {
                    tokenCallback.onFatalError("レスポンスが変だ。Famima Wifiとかにつながってない？")
                }
            }, failure = {
                tokenCallback.onFatalError(it.message!!)
            })
        }
    }
}
