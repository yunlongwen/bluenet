package com.yurnero.bluenet.ui.feature.scan

import androidx.lifecycle.viewModelScope
import com.yurnero.bluenet.data.BluetoothManager
import com.yurnero.bluenet.foundation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * ViewModel for the task [ScanScreen].
 */
@HiltViewModel
class ScanViewModel @Inject constructor(private val mBluetoothManager: BluetoothManager) :
    BaseViewModel<ScanContract.Intent, ScanContract.Event, ScanContract.State>() {

    override suspend fun handleIntents(intent: ScanContract.Intent) {
        when (intent) {
            is ScanContract.Intent.ScanDevice ->
                viewModelScope.launch {
                    mBluetoothManager.scan().catch {
                        Timber.d("" + this)
                    }.collect {
                        updateLiveData(ScanContract.Event.OnNewDevice(it))
                    }
                }
        }
    }

    override fun setInitialState(): ScanContract.State {
        return ScanContract.State(mutableListOf(), false)
    }
}