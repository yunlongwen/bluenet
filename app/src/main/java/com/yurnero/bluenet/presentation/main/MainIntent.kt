package com.yurnero.bluenet.presentation.main

import com.yurnero.bluenet.foundation.ViewIntent

sealed class MainIntent : ViewIntent
object ScanDevice : MainIntent()
