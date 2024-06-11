package cu.xetid.dtvc.androidtrainingapp.home.homescreen.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import cu.xetid.dtvc.androidtrainingapp.home.homescreen.viewmodel.HelpViewModel
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.routes.favorite.FavoriteRoute
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.routes.update.UpdateRoute

@Composable
fun HelpScreen(viewModel: HelpViewModel = hiltViewModel()) {

    Scaffold {
        HelpContent(it, navigateTo = viewModel::navigateTo)
    }

}

@Composable
fun HelpContent(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    navigateTo: (String) -> Unit
) {

    LazyColumn(
        modifier = modifier
            .padding(paddingValues)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(text = "Help screen")
            Button(onClick = { navigateTo.invoke(FavoriteRoute.FavoriteScreenRoute.route) }) {
                Text(text = "NavToHome")
            }
            Button(onClick = { navigateTo.invoke(UpdateRoute.UpdateScreenRoute.route+"1") }) {
                Text(text = "NavToUpdate")
            }
        }
    }

}
