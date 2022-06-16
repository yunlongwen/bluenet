package com.yurnero.bluenet.foundation

import android.os.Bundle
import androidx.activity.ComponentActivity

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