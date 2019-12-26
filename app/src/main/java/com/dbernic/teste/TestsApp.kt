package com.dbernic.teste

import android.app.Application
import com.dbernic.teste.entities.TestSet
import com.dbernic.teste.utility.DataUtils
import io.realm.Realm
import unibank.az.albaliplus.utils.PreferencesManager

class TestsApp : Application() {

    companion object {
        var instance: Application? = null
        lateinit var realm: Realm
        lateinit var testSet: TestSet

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        PreferencesManager.initializeInstance(this)

        Realm.init(this)
        realm = Realm.getDefaultInstance()
        val data: TestSet? = DataUtils.getData()
        testSet = if (data != null) data!! else TestSet(arrayListOf())
    }

}