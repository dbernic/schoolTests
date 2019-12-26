package com.dbernic.teste.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import unibank.az.albaliplus.utils.LocaleHelper
import java.util.*

open class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setAppLanguage()
    }

    private fun setAppLanguage() {
        var currentLanguage = LocaleHelper.getLanguage(this)
        if (currentLanguage == null) {
            currentLanguage = Locale.getDefault().language
            LocaleHelper.setLocale(this, currentLanguage)
        }


    }

}