package com.dbernic.teste.activities

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.dbernic.teste.R
import com.dbernic.teste.TestsApp
import com.dbernic.teste.entities.Question
import com.dbernic.teste.entities.Test
import com.dbernic.teste.views.AnswerLessView
import com.dbernic.teste.views.AnswerView
import com.dbernic.teste.views.QuestionNumView
import com.dbernic.teste.views.TestNameView
import kotlinx.android.synthetic.main.activity_lesson.*
import kotlinx.android.synthetic.main.activity_prepare.*

class LessonActivity : AppCompatActivity() {

    var questionNums = arrayListOf<QuestionNumView>()
    lateinit var currQuest : Question

    lateinit var currTest : Test
    var corrAnsw = 0
    var nonCorrAnsw = 0

    var listMode = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)

        fillList()
    }

    override fun onBackPressed() {
        if (listMode){
            super.onBackPressed()
        } else {
            fillList()
        }
    }

    private fun fillList(){
        listMode = true
        lessTestsLl.visibility = View.VISIBLE
        lessChooseLl.visibility = View.GONE
        lessTestsLl.removeAllViews()
        for(item in TestsApp.testSet.tests){
            val v = TestNameView(this, item.name)
            val view = v.createView()
            view.setOnClickListener {
                chooseTest(item)
            }
            lessTestsLl.addView(view)

        }
    }

    private fun chooseTest(test: Test){
        listMode = false
        lessTestsLl.visibility = View.GONE
        lessChooseLl.visibility = View.VISIBLE
        corrAnsw = 0
        nonCorrAnsw = 0

        currTest = test

        questionNums.clear()
        qnumLessLl.removeAllViews()
        for (i in 0..test.questions.size-1){
            val v = QuestionNumView(this, i+1)
            val view = v.createView()
            view.setOnClickListener {
                chooseQuestion(i)
            }
            qnumLessLl.addView(view)
            questionNums.add(v)
        }

        chooseQuestion(0)

    }

    private fun chooseQuestion(num: Int){
        var i = 0
        for (item in currTest.questions){
            if (i==num) {
                currQuest = item
                questionNums[i].setActive()
                questionLessTextTv.setText(item.text)
                fillAnswers(num)
            } else {
                questionNums[i].setInactive()
            }
            i++
        }
    }

    private fun fillAnswers(num: Int){
        answerLessLl.removeAllViews()
        for(item in currQuest.variants){
            val v = AnswerLessView(this, item)
            val view = v.createView()
            view.setOnClickListener {
                if (item.isCorrect){
                    showAlert(getString(R.string.answer), getString(R.string.correct))
                    corrAnsw++
                } else{
                    showAlert(getString(R.string.answer), getString(R.string.incorrect))
                    nonCorrAnsw++
                }
                if (num == currTest.questions.size) {
                    showAlert(
                        getString(R.string.total),
                        getString(R.string.tot_correct)+" "+corrAnsw.toString()+", "+getString(R.string.tot_incorr)+" "+nonCorrAnsw.toString()
                    )
                    fillList()
                } else {
                    chooseQuestion(num+1)
                }
            }
            answerLessLl.addView(view)


        }
    }

    fun showAlert(title: String, body: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setCancelable(false)
        builder.setMessage(body)
        builder.setNegativeButton(android.R.string.ok, object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                dialog!!.cancel()
            }
        })

        builder.create().show()
    }
}
