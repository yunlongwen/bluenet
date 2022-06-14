package com.yurnero.bluenet.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yurnero.bluenet.di.ServiceLocator
import com.yurnero.bluenet.domain.BluetoothManager
import com.yurnero.bluenet.foundation.BaseViewModel
import kotlinx.coroutines.flow.onEach

class MainViewModel(private val mBluetoothManager: BluetoothManager) :
    BaseViewModel<MainIntent, MainAction, MainState>() {

    override suspend fun handleIntents(intent: MainIntent) {
        when (intent) {
            is ScanDevice -> mBluetoothManager.scan().onEach {
                updateLiveData(OnNewDevice(it))
            }
        }

    }
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(ServiceLocator.bluetoothManager) as T
    }
}