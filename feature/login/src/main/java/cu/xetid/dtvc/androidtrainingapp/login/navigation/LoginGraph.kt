package cu.xetid.dtvc.androidtrainingapp.login.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import cu.xetid.dtvc.androidtrainingapp.login.LoginScreen
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.routes.login.LogInRoute


fun NavGraphBuilder.logInGraph() {

    navigation(
        startDestination = LogInRoute.LoginRoute.route,
        route = LogInRoute.RootRoute.route
    ) {
        composable(route = LogInRoute.LoginRoute.route) {
            LoginScreen()
        }
    }

}
