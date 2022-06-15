package com.yurnero.bluenet

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.yurnero.bluenet.foundation.BaseActivity
import com.yurnero.bluenet.presentation.ScanScreen
import com.yurnero.bluenet.presentation.scan.ScanContract
import com.yurnero.bluenet.presentation.scan.ScanViewModel
import com.yurnero.bluenet.ui.theme.BlueNetTheme
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity :
    BaseActivity<ScanContract.Intent, ScanContract.Event, ScanContract.State, ScanViewModel>() {

    override val viewModel: ScanViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlueNetTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ScanScreen()
                }
            }
        }
    }

    override fun render(event: ScanContract.Event) {
        when (event) {
            is ScanContract.Event.OnNewDevice -> {
                Timber.d("wyl,OnNewDevice:${event.advertisement.name}")
            }
        }
    }
}