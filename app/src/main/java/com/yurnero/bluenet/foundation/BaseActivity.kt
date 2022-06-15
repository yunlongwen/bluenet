package com.yurnero.bluenet.foundation

import android.os.Bundle
import androidx.activity.ComponentActivity

/**
 * @param INTENT UserIntent class represents the different actions the user can do
 * on a particular view, and in our case the user may want to retrieve the data or
 * refresh the view to get the new information.
 *
 * @param EVENT Define one-time events such as toast and page closing events.
 *
 * @param STATE It represents an immutable state of sight.A new state is created by the
 * ViewModel each time the view needs to be updated.
 */
abstract class BaseActivity<INTENT : ViewIntent, EVENT : ViewEvent, STATE : ViewState,
        VM : BaseViewModel<INTENT, EVENT, STATE>> :
    IViewRenderer<EVENT>, ComponentActivity() {

    private lateinit var event: EVENT
    val mEvent get() = event

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
        viewModel.event.observe(this) {
            event = it
            render(event)
        }
    }
}