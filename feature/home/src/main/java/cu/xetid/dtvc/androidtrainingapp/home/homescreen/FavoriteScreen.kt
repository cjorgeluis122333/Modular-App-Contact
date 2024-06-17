package cu.xetid.dtvc.androidtrainingapp.home.homescreen

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
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.routes.insertion.InsertionRoute

@Composable
fun FavoriteScreen(viewModel: FavoriteViewModel = hiltViewModel()) {

    Scaffold {
        FavoriteContent(it, navigateTo = viewModel::navigateTo)
    }

}


@Composable
fun FavoriteContent(paddingValues: PaddingValues, modifier: Modifier = Modifier, navigateTo:(String)->Unit) {
    LazyColumn(
        modifier = modifier
            .padding(paddingValues)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        item {
            Text(text = "This is the Favorite screen")
           Button(onClick = { navigateTo.invoke(InsertionRoute.InsertionScreenRoute.route) }) {

           }
        }

    }
}
