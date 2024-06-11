package cu.xetid.dtvc.androidtrainingapp.ui.navigation.routes.update

import cu.xetid.dtvc.androidtrainingapp.ui.navigation.routes.Route


sealed interface UpdateRoute : Route {

    data object RootRoute : UpdateRoute {
        override val analyticsTag = "update-flow"
        override val route = "update-rote"
    }

    data object UpdateScreenRoute : UpdateRoute {
        override val analyticsTag = "update-screen-flow"
        override val route = "update"
    }

}
