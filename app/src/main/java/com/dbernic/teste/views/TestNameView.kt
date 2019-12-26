package com.dbernic.teste.views

import android.content.Context
import android.view.View
import android.widget.TextView
import com.dbernic.teste.R

class TestNameView (val context: Context, val name: String) {

    lateinit var v: View

    fun createView(): View{
        v = View.inflate(context, R.layout.item_name, null)
        v.findViewById<TextView>(R.id.testNameText).setText(name)
        return v
    }


    fun setActive(){
        v.setBackgroundColor(context.resources.getColor(android.R.color.holo_green_light))
    }

    fun setInactive(){
        v.setBackgroundColor(context.resources.getColor(android.R.color.background_light))
    }
}