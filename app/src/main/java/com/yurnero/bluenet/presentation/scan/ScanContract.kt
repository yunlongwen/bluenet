package com.yurnero.bluenet.presentation.scan

import com.yurnero.bluenet.foundation.ViewEvent
import com.yurnero.bluenet.foundation.ViewIntent
import com.yurnero.bluenet.foundation.ViewState
import com.yurnero.bluetooth.Advertisement

class ScanContract {
    sealed class Event : ViewEvent {
        class OnNewDevice(val advertisement: Advertisement) : Event()
    }

    sealed class Intent : ViewIntent {
        object ScanDevice : Intent()
    }

    data class State(val advertisementList: List<Advertisement>, val isScanning: Boolean) :
        ViewState
}
