package cu.xetid.dtvc.androidtrainingapp.home.update.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import cu.xetid.dtvc.androidtrainingapp.home.update.screen.UpdateScreen
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.routes.update.UpdateRoute

fun NavGraphBuilder.updateGraph() {

    navigation(
        startDestination = UpdateRoute.UpdateScreenRoute.route,
        route = UpdateRoute.RootRoute.route
    ) {
        composable(
            route = UpdateRoute.UpdateScreenRoute.route + "{userId}",
            arguments = listOf(navArgument(name = "userId") { type = NavType.StringType })
        ) {
            stackEntry->
            val getUserId= stackEntry.arguments?.getString("userId")?:""
            UpdateScreen(userId = getUserId)
        }
    }

}