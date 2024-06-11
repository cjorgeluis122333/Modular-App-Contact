package cu.xetid.dtvc.androidtrainingapp.ui.navigation.routes.insertion

import cu.xetid.dtvc.androidtrainingapp.ui.navigation.routes.Route


sealed interface InsertionRoute : Route {

    data object RootRoute : InsertionRoute {
        override val analyticsTag = "insertion-flow"
        override val route = "insertion-rote"
    }

    data object InsertionScreenRoute : InsertionRoute {
        override val analyticsTag = "insertion-screen-flow"
        override val route = "insertion"
    }

}
