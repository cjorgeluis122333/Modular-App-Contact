package cu.xetid.dtvc.androidtrainingapp.home

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.routes.login.LogInRoute

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    Content(navigateTo = viewModel::navigateTo)
}

@Composable
fun Content(
    navigateTo: (String) -> Unit,
) {
    Text(
        text = "Esta es la Home Screen"
    )

    TextButton(
        onClick = {
            navigateTo(LogInRoute.RootRoute.route)
        },
        content = {
            Text(
                text = "Para el login"
            )
        }
    )

}
