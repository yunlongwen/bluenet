package com.yurnero.bluenet.foundation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

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
abstract class BaseActivity<INTENT : ViewIntent, ACTION : Action, STATE : ViewState,
        VM : BaseViewModel<INTENT, ACTION, STATE>> :
    IViewRenderer<STATE>, ComponentActivity() {

    private lateinit var viewState: STATE
    val mState get() = viewState

    /**
     * Abstract [BaseViewModel] to be overridden by implementation class.
     *
     * The viewModel can be provided using following snippet
     *
     * ```
     * override val viewModel: MainViewModel by viewModels()
     * ```
     */
    abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.viewState.observe(this) {
            viewState = it
            render(viewState)
        }
    }
}