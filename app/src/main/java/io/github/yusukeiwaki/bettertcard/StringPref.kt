package io.github.yusukeiwaki.bettertcard

import android.content.SharedPreferences
import kotlin.reflect.KProperty

class StringPref(private val sharedPreferences: SharedPreferences, private val defaultValue: String? = null) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String? {
        return sharedPreferences.getString(property.name, defaultValue)
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String?) {
        if (value == null) {
            sharedPreferences.edit()
                .remove(property.name)
                .apply()
        } else {
            sharedPreferences.edit()
                .putString(property.name, value)
                .apply()
        }
    }
}