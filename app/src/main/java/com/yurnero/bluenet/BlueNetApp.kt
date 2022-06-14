package com.yurnero.bluenet

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.yurnero.bluenet.di.ServiceLocator

class BlueNetApp : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        ServiceLocator.init()
    }
}