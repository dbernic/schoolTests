package com.dbernic.teste.utility

import com.dbernic.teste.TestsApp
import com.dbernic.teste.entities.AppData
import com.dbernic.teste.entities.Test
import com.dbernic.teste.entities.TestSet
import com.google.gson.Gson
import java.lang.reflect.Type

object DataUtils{

    public  fun saveData(){
        TestsApp.realm.beginTransaction()
        val appData = AppData()
        appData.data = Gson().toJson(TestsApp.testSet)
        appData.id = "tests"
        TestsApp.realm.insertOrUpdate(appData)
        TestsApp.realm.commitTransaction()
    }

    public fun getData(): TestSet?{
        val result = TestsApp.realm.where(AppData::class.java).equalTo("id", "tests").findFirst()
        if (result == null) return null
        return Gson().fromJson(result.data, TestSet::class.java)
    }
}