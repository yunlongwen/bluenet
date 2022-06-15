package com.yurnero.bluenet.foundation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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
 * @param EVENT  Define one-time events such as toast and page closing events.
 *
 * @param STATE It represents an immutable state of sight.A new state is created by the
 * ViewModel each time the view needs to be updated.
 */
abstract class BaseViewModel<INTENT : ViewIntent, EVENT : ViewEvent, STATE : ViewState> :
    ViewModel() {
    private val intentChannel = Channel<INTENT>(Channel.UNLIMITED)
    private val mutableLiveData: MutableLiveData<EVENT> = MutableLiveData()

    val event: LiveData<EVENT>
        get() = mutableLiveData


    abstract fun setInitialState(): STATE
    private val initialState: STATE by lazy { setInitialState() }
    private val _viewState: MutableState<STATE> = mutableStateOf(initialState)
    val viewState: State<STATE> = _viewState

    init {
        viewModelScope.launch {
            processIntents()
        }
    }

    fun updateLiveData(event: EVENT) {
        viewModelScope.launch {
            mutableLiveData.value = event
        }
    }

    fun dispatchIntent(intent: INTENT) {
        if (!event.hasObservers()) {
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


interface IViewRenderer<EVENT> {
    fun render(state: EVENT)
}