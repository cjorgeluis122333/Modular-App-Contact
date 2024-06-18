package cu.xetid.dtvc.androidtrainingapp.home.screen

import androidx.compose.material3.Scaffold

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import cu.xetid.dtvc.androidtrainingapp.home.screen.component.ContactButtonBar
import cu.xetid.dtvc.androidtrainingapp.home.viewmodel.HomeViewModel
import cu.xetid.dtvc.androidtrainingapp.ui.component.GenericTopAppBar

@Composable
fun FavoriteScreen(viewModel: HomeViewModel = hiltViewModel()) {

    LaunchedEffect(viewModel) {
        viewModel.selectFavoriteContact()
    }

    val selectFavorite by viewModel.selectFavorite.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val isError by viewModel.isError.collectAsState()

    Scaffold(topBar = {
        GenericTopAppBar(
            title = "Favorite",
            isNavigationIconEnable = false
        )
    },  bottomBar = {
        ContactButtonBar(navigateTo = viewModel::navigateTo)
    }

        ) {
        HomeContent(
            paddingValues = it,
            navigateTo = viewModel::navigateTo,
            selectAll = selectFavorite,
            isError = isError,
            onDelete = viewModel::deleteContact,
            isLoading = isLoading
        )
    }

}


