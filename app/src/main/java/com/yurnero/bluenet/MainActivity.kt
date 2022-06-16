package com.yurnero.bluenet

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.yurnero.bluenet.foundation.BaseActivity
import com.yurnero.bluenet.ui.feature.main.MainContract
import com.yurnero.bluenet.ui.feature.main.MainViewModel
import com.yurnero.bluenet.ui.navigation.AppNavigation
import com.yurnero.bluenet.ui.theme.BlueNetTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity :
    BaseActivity<MainContract.Intent, MainContract.Event, MainContract.State, MainViewModel>() {

    override val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlueNetTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AppNavigation()
                }
            }
        }
    }

    override fun render(event: MainContract.Event) {

    }
}