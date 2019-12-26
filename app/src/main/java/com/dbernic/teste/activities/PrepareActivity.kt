package com.dbernic.teste.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.dbernic.teste.R
import com.dbernic.teste.TestsApp
import com.dbernic.teste.entities.Answer
import com.dbernic.teste.entities.Question
import com.dbernic.teste.entities.Test
import com.dbernic.teste.utility.DataUtils
import com.dbernic.teste.views.AnswerView
import com.dbernic.teste.views.QuestionNumView
import com.dbernic.teste.views.TestNameView
import kotlinx.android.synthetic.main.activity_prepare.*
import kotlin.math.absoluteValue

class PrepareActivity : AppCompatActivity(), View.OnClickListener {

    var questionNums = arrayListOf<QuestionNumView>()
    var testNames = arrayListOf<TestNameView>()
    var answersViews = arrayListOf<AnswerView>()

    lateinit var currTest : Test
    lateinit var currQuest : Question


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prepare)

        if (TestsApp.testSet.tests.isEmpty()){
            questionsLl.visibility = View.GONE
        } else {
            currTest = TestsApp.testSet.tests[0]
            currQuest = currTest.questions[0]
            questionTextTv.setText(currQuest.text)
            fillTests()
            chooseTest(TestsApp.testSet.tests[0])
        }

        addTest.setOnClickListener(this)
        addQuestion.setOnClickListener(this)
        addAnswer.setOnClickListener(this)
    }

    override fun onBackPressed() {
        DataUtils.saveData()
        super.onBackPressed()
    }

    override fun onClick(v: View) {
        when (v.id){
            R.id.addTest -> {
                val name = newTest.text.toString()
                if (name.equals("")) return
                for(item in TestsApp.testSet.tests){
                    if (name.equals(item.name) ) return
                }

                val answer = Answer("", false)
                val question  = Question("", arrayListOf(answer))
                val testItem = Test(name, arrayListOf(question))
                TestsApp.testSet.tests.add(testItem)
                DataUtils.saveData()
                fillTests(TestsApp.testSet.tests.size-1)
            }

            R.id.addQuestion -> {
                currTest.questions.add(Question("", arrayListOf()))
                chooseTest(currTest)
            }

            R.id.addAnswer -> {
                val item = Answer("", false)
                addAnswer(item)
            }
        }
    }

    private fun fillTests(active: Int = 0){
        var i = 0
        testsLl.removeAllViews()
        for(item in TestsApp.testSet.tests){
            val v = TestNameView(this, item.name)
            val view = v.createView()
            view.setOnClickListener {
                chooseTest(item)
            }
            testsLl.addView(view)
            testNames.add(v)
            if (i==active){
                v.setActive()
                currTest = item
            } else{
                v.setInactive()
            }

            i++
        }
    }

    private fun chooseTest(test: Test){
        saveData()
        currTest = test
        for (item in testNames){
            if (item.name==test.name) item.setActive() else item.setInactive()
        }
        questionNums.clear()
        qnumLl.removeAllViews()
        for (i in 0..test.questions.size-1){
            val v = QuestionNumView(this, i+1)
            val view = v.createView()
            view.setOnClickListener {

                chooseQuestion(i)
            }
            qnumLl.addView(view)
            questionNums.add(v)
        }

        chooseQuestion(0)

    }

    private fun chooseQuestion(num: Int){
        saveData()
        var i = 0
        for (item in currTest.questions){
            if (i==num) {
                currQuest = item
                questionNums[i].setActive()
                questionTextTv.setText(item.text)
                fillAnswers()
            } else {
                questionNums[i].setInactive()
            }
            i++
        }
    }

    private fun fillAnswers(){
        answersViews.clear()
        answerLl.removeAllViews()
        for(item in currQuest.variants){
            addAnswer(item)
        }
    }

    private fun addAnswer(item: Answer){
        val v = AnswerView(this, item)
        answerLl.addView(v.createView())
        answersViews.add(v)
    }

    private fun saveData(){
        if (answersViews.size > 0){
            currQuest.variants.clear()
            for (item in answersViews){
                currQuest.variants.add(item.getData())
            }
        }
        currQuest.text = questionTextTv.text.toString()
        DataUtils.saveData()
    }


}
