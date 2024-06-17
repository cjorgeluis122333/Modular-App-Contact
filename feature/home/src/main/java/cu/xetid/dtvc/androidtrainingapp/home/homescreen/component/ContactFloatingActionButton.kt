package cu.xetid.dtvc.androidtrainingapp.home.homescreen.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.routes.insertion.InsertionRoute

@Composable
fun ContactFloatingActionButton(navigateToInsert:(String)->Unit={}) {
    FloatingActionButton(onClick = { navigateToInsert.invoke(InsertionRoute.InsertionScreenRoute.route) }) {
        Icon(imageVector = Icons.Filled.Add , contentDescription = "insertion")
    }
}