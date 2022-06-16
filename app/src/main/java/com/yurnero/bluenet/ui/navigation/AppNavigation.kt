package com.yurnero.bluenet.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.yurnero.bluenet.ui.feature.connection.ConnectionScreen
import com.yurnero.bluenet.ui.feature.main.MainScreen
import com.yurnero.bluenet.ui.feature.scan.ScanScreen
import com.yurnero.bluenet.ui.navigation.Navigation.Args.PERIPHERAL_ID

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Navigation.Router.MAIN) {

        composable(route = Navigation.Router.MAIN) {
            MainScreen(navController = navController)
        }

        composable(route = Navigation.Router.PERIPHERALS) {
            ScanScreen(navController = navController)
        }

        composable(
            route = Navigation.Router.CONNECTION,
            arguments = listOf(navArgument(name = PERIPHERAL_ID) {
                type = NavType.StringType
            })
        ) {
            val peripheralId =
                requireNotNull(it.arguments?.getString(PERIPHERAL_ID)) {
                    "peripheral id is required as an argument"
                }
            ConnectionScreen(navController = navController, peripheralId = peripheralId)
        }
    }
}

object Navigation {
    object Args {
        const val PERIPHERAL_ID = "peripheral_id"
    }

    object Router {
        const val MAIN = "main"
        const val PERIPHERALS = "peripheral"
        const val CONNECTION = "$PERIPHERALS/{$PERIPHERAL_ID}"
    }
}

fun NavController.navigateToScanning() {
    navigate(route = Navigation.Router.PERIPHERALS)
}

fun NavController.navigateToConnection(peripheralId: String) {
    navigate(route = "${Navigation.Router.PERIPHERALS}/$peripheralId")
}