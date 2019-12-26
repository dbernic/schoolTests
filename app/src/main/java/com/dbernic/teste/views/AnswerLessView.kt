package com.dbernic.teste.views

import android.content.Context
import android.view.View
import android.widget.TextView
import com.dbernic.teste.R
import com.dbernic.teste.entities.Answer

class AnswerLessView (val context: Context, val answer: Answer) {

    lateinit var v: View

    fun createView(): View{
        v = View.inflate(context, R.layout.item_qnum, null)
        v.findViewById<TextView>(R.id.qnumText).setText(answer.value)
        return v
    }


}