package com.yurnero.bluenet.foundation

import androidx.activity.ComponentActivity

abstract class BaseActivity<INTENT : ViewIntent, EFFECT : ViewEffect, STATE : ViewState,
        VM : BaseViewModel<INTENT, EFFECT, STATE>> : ComponentActivity() {

    private lateinit var _effect: EFFECT
    val effect get() = _effect

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
}