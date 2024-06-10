package cu.xetid.dtvc.androidtrainingapp.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.routes.home.HomeRoute

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel()
) {
    Content(navigateTo = viewModel::navigateTo)
}

@Composable
fun Content(
    navigateTo: (String) -> Unit,
) {
    Column (modifier = Modifier.fillMaxSize()) {

        Text(
            text = "Esta es la Login Screen"
        )

        TextButton(
            onClick = {
                navigateTo(HomeRoute.RootRoute.route)
            },
            content = {
                Text(
                    text = "Para el Home"
                )
            }
        )
    }

}
