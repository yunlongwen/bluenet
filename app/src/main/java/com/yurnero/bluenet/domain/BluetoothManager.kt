package com.yurnero.bluenet.domain

import com.yurnero.bluetooth.Advertisement
import com.yurnero.bluetooth.Scanner
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface BluetoothManager {
    fun scan(): Flow<Advertisement>
}

class BluetoothManagerImp @Inject constructor() : BluetoothManager {
    override fun scan(): Flow<Advertisement> {
        return Scanner()
            .advertisements
    }

}