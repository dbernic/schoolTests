package com.dbernic.teste.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dbernic.teste.R
import kotlinx.android.synthetic.main.activity_main.*
import unibank.az.albaliplus.utils.LocaleHelper

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initListeners()
    }

    private fun initListeners(){
        md_flag.setOnClickListener {
            applyLanguage("ro")
        }

        ru_flag.setOnClickListener {
            applyLanguage("ru")
        }

        en_flag.setOnClickListener {
            applyLanguage("en")
        }

        prepare_btn.setOnClickListener {
            startActivity(
                Intent(this, PrepareActivity::class.java)
            )
        }

        lesson_btn.setOnClickListener {
            startActivity(
                Intent(this, LessonActivity::class.java)
            )
        }

    }

    private fun applyLanguage(lang: String) {
        LocaleHelper.setLocale(this, lang)

        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}
