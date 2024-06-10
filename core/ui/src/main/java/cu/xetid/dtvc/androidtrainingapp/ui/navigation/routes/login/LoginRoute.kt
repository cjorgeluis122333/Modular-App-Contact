package cu.xetid.dtvc.androidtrainingapp.ui.navigation.routes.login

import cu.xetid.dtvc.androidtrainingapp.ui.navigation.routes.Route

sealed interface LogInRoute : Route {

    data object RootRoute : LogInRoute {
        override val analyticsTag = "login-flow"
        override val route = "sign-in"
    }

    data object LoginRoute : LogInRoute {
        override val analyticsTag = "login-screen-flow"
        override val route = "login"
    }
}
