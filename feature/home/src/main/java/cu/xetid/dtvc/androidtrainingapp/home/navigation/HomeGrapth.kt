package cu.xetid.dtvc.androidtrainingapp.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import cu.xetid.dtvc.androidtrainingapp.home.homescreen.FavoriteScreen
import cu.xetid.dtvc.androidtrainingapp.home.homescreen.HelpScreen
import cu.xetid.dtvc.androidtrainingapp.home.homescreen.HomeScreen
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.routes.favorite.FavoriteRoute
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.routes.help.HelpRoute
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.routes.home.HomeRoute

fun  NavGraphBuilder.homeGraph() {

    navigation(startDestination = HomeRoute.HomeScreenRoute.route, route = HomeRoute.RootRoute.route){

        composable(HomeRoute.HomeScreenRoute.route){
            HomeScreen()
        }
        composable(HelpRoute.HelpScreenRoute.route) {
            HelpScreen()
        }
        composable(FavoriteRoute.FavoriteScreenRoute.route){
            FavoriteScreen()
        }
    }

}