package io.github.yusukeiwaki.bettertcard.shufoo

import android.content.Context
import io.github.yusukeiwaki.bettertcard.StringPref

class MyPref(private val context: Context) {
    private val sharedPreferences
        get() = context.getSharedPreferences("my_pref", Context.MODE_PRIVATE)

    var token by StringPref(sharedPreferences)
}
