package com.dbernic.teste.views

import android.content.Context
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import com.dbernic.teste.R
import com.dbernic.teste.entities.Answer

class AnswerView (val context: Context, val answer: Answer) {

    lateinit var name: EditText
    lateinit var correct: CheckBox

    fun createView(): View{
        val v = View.inflate(context, R.layout.item_answer, null)
        name = v.findViewById<EditText>(R.id.answerText)
        correct = v.findViewById<CheckBox>(R.id.answerCorrect)

        name.setText(answer.value)
        correct.isChecked = answer.isCorrect
        return v
    }

    fun getData() : Answer{
        return Answer(name.text.toString(), correct.isChecked)
    }
}