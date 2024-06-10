package cu.xetid.dtvc.androidtrainingapp.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import cu.xetid.dtvc.androidtrainingapp.home.HomeScreen
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.routes.home.HomeRoute

fun NavGraphBuilder.homeGraph() {

    navigation(
        startDestination = HomeRoute.HomeScreenRoute.route,
        route = HomeRoute.RootRoute.route
    ) {
        composable(route = HomeRoute.HomeScreenRoute.route) {
            HomeScreen()
        }
    }

}