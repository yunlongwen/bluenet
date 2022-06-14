package com.yurnero.bluenet.presentation.main

import com.yurnero.bluenet.foundation.ViewState
import com.yurnero.bluetooth.Advertisement

sealed class MainState : ViewState
class OnNewDevice(val advertisement: Advertisement) : MainState()
