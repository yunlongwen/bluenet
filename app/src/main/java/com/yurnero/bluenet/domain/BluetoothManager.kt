package com.yurnero.bluenet.domain

import com.yurnero.bluenet.BlueNetApp
import com.yurnero.bluetooth.Advertisement
import com.yurnero.bluetooth.Scanner
import kotlinx.coroutines.flow.Flow

interface BluetoothManager {
    fun scan(): Flow<Advertisement>
}

class BluetoothManagerImp : BluetoothManager {
    override fun scan(): Flow<Advertisement> {
        return Scanner()
            .advertisements
    }

}