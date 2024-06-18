package com.example.insertion.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.insertion.screen.InsertionScreen
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.routes.insertion.InsertionRoute

fun NavGraphBuilder.insertGraph() {
    navigation(
        startDestination = InsertionRoute.InsertionScreenRoute.route,
        route = InsertionRoute.RootRoute.route

    ) {
        composable(route = InsertionRoute.InsertionScreenRoute.route) {
            InsertionScreen()
        }
    }
}