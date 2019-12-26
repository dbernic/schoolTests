package unibank.az.albaliplus.utils

import android.content.Context
import android.content.SharedPreferences
import com.dbernic.teste.utility.Constants

class PreferencesManager(context: Context) {

    companion object {
        lateinit var instance: PreferencesManager

        @Synchronized
        fun initializeInstance(context: Context) {

            instance = PreferencesManager(context)

        }

    }

    private var pref: SharedPreferences = context.getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE)




    fun setValue(key: String, value: String) {
        pref.edit().putString(key, value).commit()
    }

    fun getStringValue(key: String): String {
        return pref.getString(key, "")
    }

    fun setValue(key: String, value: Int) {
        pref.edit().putInt(key, value).commit()
    }

    fun getIntValue(key: String): Int {
        return pref.getInt(key, 0)
    }

    fun remove(key: String) {
        pref.edit().remove(key).commit()
    }

    fun getLongValue(key: String): Long {
        return pref.getLong(key, 0)
    }

    fun setValue(key: String, value: Long) {
        pref.edit().putLong(key, value).commit()
    }

    fun getBoolValue(key: String): Boolean {
        return pref.getBoolean(key, false)
    }

    fun setValue(key: String, `val`: Boolean) {
        pref.edit().putBoolean(key, `val`).commit()
    }

    fun clear(): Boolean {
        return pref.edit().clear().commit()
    }
}