package com.yurnero.bluenet.foundation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * @param INTENT UserIntent class represents the different actions the user can do
 * on a particular view, and in our case the user may want to retrieve the data or
 * refresh the view to get the new information.
 *
 * @param ACTION
 *
 * @param STATE It represents an immutable state of sight.A new state is created by the
 * ViewModel each time the view needs to be updated.
 */
abstract class BaseViewModel<INTENT : ViewIntent, ACTION : Action, STATE : ViewState> :
    ViewModel() {
    private val intentChannel = Channel<INTENT>(Channel.UNLIMITED)
    private val mutableLiveData: MutableLiveData<STATE> = MutableLiveData()

    val viewState: LiveData<STATE>
        get() = mutableLiveData

    init {
        viewModelScope.launch {
            processIntents()
        }
    }

    fun updateLiveData(state: STATE) {
        viewModelScope.launch {
            mutableLiveData.value = state
        }
    }

    fun dispatchIntent(intent: INTENT) {
        if (!viewState.hasObservers()) {
            Timber.e("No LiveData observer attached.")
        }

        Timber.d("processing viewIntent: $intent")
        intentChannel.trySend(intent)
    }

    private suspend fun processIntents() {
        intentChannel.consumeAsFlow().collect {
            handleIntents(it)
        }
    }

    /**
     * To be overridden by the Implementation class to handle Intent
     */
    protected abstract suspend fun handleIntents(intent: INTENT)
}


interface IViewRenderer<STATE> {
    fun render(state: STATE)
}