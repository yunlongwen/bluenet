package com.yurnero.bluenet.ui.feature.scan

import com.yurnero.bluenet.foundation.ViewEffect
import com.yurnero.bluenet.foundation.ViewIntent
import com.yurnero.bluenet.foundation.ViewState
import com.yurnero.bluetooth.Advertisement

class ScanContract {
    sealed class Effect : ViewEffect {
        sealed class Navigation : Effect() {
            data class ToConnection(val peripheralId: String) : Navigation()
        }
    }

    sealed class Intent : ViewIntent {
        object Retry : Intent()
    }

    data class State(
        val advertisements: MutableList<Advertisement>? = null,
        val isScanning: Boolean,
        val isError: Boolean
    ) : ViewState
}
