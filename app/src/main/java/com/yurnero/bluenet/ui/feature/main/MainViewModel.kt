package com.yurnero.bluenet.ui.feature.main

import com.yurnero.bluenet.foundation.BaseViewModel
import com.yurnero.bluenet.ui.feature.main.MainContract

/**
 * ViewModel for the task [MainScreen].
 */
class MainViewModel :
    BaseViewModel<MainContract.Intent, MainContract.Event, MainContract.State>() {

    override suspend fun handleIntents(intent: MainContract.Intent) {

    }

    override fun setInitialState(): MainContract.State {
        return MainContract.State(true)
    }
}