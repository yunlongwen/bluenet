package com.yurnero.bluenet.foundation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber

interface ViewIntent

interface ViewEffect

interface ViewState

/**
 * @param INTENT UserIntent class represents the different actions the user can do
 * on a particular view, and in our case the user may want to retrieve the data or
 * refresh the view to get the new information.
 *
 * @param EFFECT plain object that signals one-time side-effect actions that should impact the UI
 * e.g. triggering a navigation action, showing a Toast, SnackBar etc.
 *  Effects are exposed as ChannelFlow which behave as in each event is delivered to a
 *  single subscriber. An attempt to post an event without subscribers
 *  will suspend as soon as the channel buffer becomes full, waiting for a subscriber to appear.
 *
 * @param STATE It represents an immutable state of sight.A new state is created by the
 * ViewModel each time the view needs to be updated.
 */
abstract class BaseViewModel<INTENT : ViewIntent, EFFECT : ViewEffect, STATE : ViewState> :
    ViewModel() {
    private val _intent = Channel<INTENT>(Channel.UNLIMITED)

    private val _effect: MutableLiveData<EFFECT> = MutableLiveData()
    val effect: LiveData<EFFECT>
        get() = _effect

    abstract fun setInitialState(): STATE
    private val initialState: STATE by lazy { setInitialState() }
    private val _viewState: MutableStateFlow<STATE> = MutableStateFlow(initialState)
    val viewState: StateFlow<STATE> = _viewState

    init {
        subscribeToIntents()
    }

    private fun subscribeToIntents() {
        viewModelScope.launch {
            _intent.consumeAsFlow().collect {
                handleIntents(it)
            }
        }
    }

    fun dispatchIntent(intent: INTENT) {
        if (!effect.hasObservers()) {
            Timber.e("No LiveData observer attached.")
        }
        viewModelScope.launch {
            Timber.d("processing viewIntent: $intent")
            _intent.trySend(intent)
        }
    }

    protected fun setEffect(effect: EFFECT) {
        viewModelScope.launch {
            _effect.value = effect
        }
    }

    protected fun updateState(reducer: STATE.() -> STATE) {
        viewModelScope.launch {
            val newState = viewState.value.reducer()
            _viewState.emit(newState)
        }
    }

    /**
     * To be overridden by the Implementation class to handle Intent
     */
    protected abstract fun handleIntents(intent: INTENT)
}