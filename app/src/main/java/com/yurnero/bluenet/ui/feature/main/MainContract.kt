package com.yurnero.bluenet.ui.feature.main

import com.yurnero.bluenet.foundation.ViewEffect
import com.yurnero.bluenet.foundation.ViewIntent
import com.yurnero.bluenet.foundation.ViewState

class MainContract {
    sealed class Effect : ViewEffect
    sealed class Intent : ViewIntent

    data class State(val visibility: Boolean) :
        ViewState
}
