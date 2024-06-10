package cu.xetid.dtvc.androidtrainingapp.ui.navigation.routes.home

import cu.xetid.dtvc.androidtrainingapp.ui.navigation.routes.Route

sealed interface HomeRoute : Route {

    data object RootRoute : HomeRoute {
        override val analyticsTag = "home-flow"
        override val route = "home"
    }

    data object HomeScreenRoute : HomeRoute {
        override val analyticsTag = "home-screen-flow"
        override val route = "home/home-screen"
    }

}
