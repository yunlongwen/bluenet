package com.yurnero.bluenet.ui.feature.main

import com.yurnero.bluenet.foundation.BaseViewModel

/**
 * ViewModel for the task [MainScreen].
 */
class MainViewModel :
    BaseViewModel<MainContract.Intent, MainContract.Effect, MainContract.State>() {

    override fun handleIntents(intent: MainContract.Intent) {

    }

    override fun setInitialState(): MainContract.State {
        return MainContract.State(true)
    }
}