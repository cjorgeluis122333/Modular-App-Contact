package cu.xetid.dtvc.androidtrainingapp.ui.navigation.routes.favorite

import cu.xetid.dtvc.androidtrainingapp.ui.navigation.routes.Route


sealed interface FavoriteRoute : Route {

    data object RootRoute : FavoriteRoute {
        override val analyticsTag = "favorite-flow"
        override val route = "favorite-rote"
    }

    data object FavoriteScreenRoute : FavoriteRoute {
        override val analyticsTag = "favorite-screen-flow"
        override val route = "favorite"
    }

}
