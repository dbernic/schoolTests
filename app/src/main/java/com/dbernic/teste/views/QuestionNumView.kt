package com.dbernic.teste.views

import android.content.Context
import android.view.View
import android.widget.TextView
import com.dbernic.teste.R

class QuestionNumView (val context: Context, val num: Int) {

    lateinit var v: View

    fun createView(): View{
        v = View.inflate(context, R.layout.item_qnum, null)
        v.findViewById<TextView>(R.id.qnumText).setText(num.toString())
        return v
    }

    fun setActive(){
        v.setBackgroundColor(context.resources.getColor(android.R.color.holo_green_light))
    }

    fun setInactive(){
        v.setBackgroundColor(context.resources.getColor(android.R.color.background_light))
    }
}