package cu.xetid.dtvc.androidtrainingapp.home.screen

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cu.xetid.dtvc.androidtrainingapp.home.screen.component.ContactButtonBar
import cu.xetid.dtvc.androidtrainingapp.home.viewmodel.HomeViewModel
import cu.xetid.dtvc.androidtrainingapp.ui.component.GenericTopAppBar

@Composable
fun FavoriteScreen(viewModel: HomeViewModel = hiltViewModel()) {

    val selectFavoriteContactSubscriberAtUi by
        viewModel.allFavoriteContactSubscriberAtUI.collectAsStateWithLifecycle()

    val stateFavorite by viewModel.homeUIState.collectAsState()


    Scaffold(topBar = {
        GenericTopAppBar(
            title = "Favorite",
            isNavigationIconEnable = false
        )
    }, bottomBar = {
        ContactButtonBar(navigateTo = viewModel::navigateTo)
    }

    ) {
        HomeContent(
            paddingValues = it,
            navigateTo = viewModel::navigateTo,
            selectAll = selectFavoriteContactSubscriberAtUi,
            stateHome = stateFavorite,
            modifier = Modifier
        )
    }

}


