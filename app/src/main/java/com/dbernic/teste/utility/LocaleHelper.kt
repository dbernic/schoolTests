package unibank.az.albaliplus.utils

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.preference.PreferenceManager
import android.util.DisplayMetrics

import java.util.Locale

object LocaleHelper {

    private val SELECTED_LANGUAGE = "Locale.Helper.Selected.Language"


    fun getLanguage(context: Context): String {
        return getPersistedData(context, "en")!!
    }

    fun setLocale(context: Context, language: String) {

        val activityRes = context.resources
        val activityConf = activityRes.getConfiguration()
        val newLocale = Locale(language)
        activityConf.setLocale(newLocale)
        activityRes.updateConfiguration(activityConf, activityRes.getDisplayMetrics())

        val applicationRes = context.getApplicationContext().getResources()
        val applicationConf = applicationRes.getConfiguration()
        applicationConf.setLocale(newLocale)
        applicationRes.updateConfiguration(
            applicationConf,
            applicationRes.getDisplayMetrics()
        )

        persist(context, language)
    }

    private fun getPersistedData(context: Context, defaultLanguage: String?): String? {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        return preferences.getString(SELECTED_LANGUAGE, defaultLanguage)
    }

    private fun persist(context: Context, language: String) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = preferences.edit()

        editor.putString(SELECTED_LANGUAGE, language)
        editor.apply()
    }


}
