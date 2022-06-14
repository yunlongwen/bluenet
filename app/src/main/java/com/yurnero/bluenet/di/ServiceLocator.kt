package com.yurnero.bluenet.di

import android.app.Application

object ServiceLocator {
    private val domainComponent by lazy { DomainComponent() }
    val bluetoothManager by lazy { domainComponent.bluetoothManager }

    fun init() {
//        domainComponent = DomainComponent()
    }
}