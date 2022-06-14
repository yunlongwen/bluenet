package com.yurnero.bluenet.presentation.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yurnero.bluenet.foundation.BaseActivity
import com.yurnero.bluenet.ui.theme.BlueNetTheme
import timber.log.Timber

class MainActivity :
    BaseActivity<MainIntent, MainAction, MainState, MainViewModel>() {

    override val viewModel: MainViewModel by viewModels { MainViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlueNetTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting(viewModel)
                }
            }
        }
    }

    override fun render(state: MainState) {
        when (state) {
            is OnNewDevice -> {
                Timber.i("wyl,OnNewDevice:${state.advertisement.name}")
            }
        }

    }
}

@Composable
fun Greeting(viewModel: MainViewModel) {
    Button(
        onClick = { viewModel.dispatchIntent(ScanDevice) },
    ) {
        Text(
            text = "Scan device",
        )
    }
}