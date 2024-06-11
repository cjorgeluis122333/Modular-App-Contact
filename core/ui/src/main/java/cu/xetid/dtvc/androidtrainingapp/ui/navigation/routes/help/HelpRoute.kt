package cu.xetid.dtvc.androidtrainingapp.ui.navigation.routes.help

import cu.xetid.dtvc.androidtrainingapp.ui.navigation.routes.Route


sealed interface HelpRoute : Route {

    data object RootRoute : HelpRoute {
        override val analyticsTag = "help-flow"
        override val route = "help-rote"
    }

    data object HelpScreenRoute : HelpRoute {
        override val analyticsTag = "help-screen-flow"
        override val route = "help"
    }

}
