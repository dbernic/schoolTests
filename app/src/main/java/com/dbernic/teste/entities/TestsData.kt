package com.dbernic.teste.entities

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class AppData(): RealmObject(){
    @PrimaryKey
    var id: String = ""
    var data: String?=null
}

class TestSet(val tests: ArrayList<Test>)

class Test(val name: String, val questions: ArrayList<Question>)

class Question(var text: String, val variants: ArrayList<Answer>)

class Answer(var value: String, var isCorrect: Boolean)