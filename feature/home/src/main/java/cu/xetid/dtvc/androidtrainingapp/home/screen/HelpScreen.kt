package cu.xetid.dtvc.androidtrainingapp.home.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import cu.xetid.dtvc.androidtrainingapp.home.screen.component.ContactButtonBar
import cu.xetid.dtvc.androidtrainingapp.home.viewmodel.HomeViewModel
import cu.xetid.dtvc.androidtrainingapp.ui.component.GenericTopAppBar

@Composable
fun HelpScreen(viewModel: HomeViewModel = hiltViewModel()) {

    Scaffold(topBar = { GenericTopAppBar(title = "Help", isNavigationIconEnable = false) },
        bottomBar = { ContactButtonBar(navigateTo = viewModel::navigateTo) }) {
        HelpContent(it)
    }

}

@Composable
fun HelpContent(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {

    LazyColumn(
        modifier = modifier
            .padding(paddingValues)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
     Text(text = "Screen Help")
        }
    }

}
