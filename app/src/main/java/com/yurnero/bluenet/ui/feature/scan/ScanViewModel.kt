package com.yurnero.bluenet.ui.feature.scan

import androidx.lifecycle.viewModelScope
import com.yurnero.bluenet.data.BluetoothManager
import com.yurnero.bluenet.foundation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * ViewModel for the task [ScanScreen].
 */
@HiltViewModel
class ScanViewModel @Inject constructor(private val mBluetoothManager: BluetoothManager) :
    BaseViewModel<ScanContract.Intent, ScanContract.Effect, ScanContract.State>() {

    override fun handleIntents(intent: ScanContract.Intent) {
        when (intent) {
            is ScanContract.Intent.Retry -> startScan()
        }
    }

    override fun setInitialState(): ScanContract.State {
        return ScanContract.State(isScanning = false, isError = false)
    }

    private fun startScan() {
        updateState { copy(isScanning = true) }
        viewModelScope.launch {
            mBluetoothManager.scan()
                .catch {
                    Timber.d("" + this)
                }.distinctUntilChangedBy {
                    it.address
                }
                .collect {
                    updateState {
                        copy(advertisements = advertisements?.apply {
                            advertisements.add(
                                it
                            )
                        }, isScanning = true)
                    }
                }
        }
    }
}