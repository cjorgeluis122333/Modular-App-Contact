package cu.xetid.dtvc.androidtrainingapp.home.homescreen

import androidx.lifecycle.ViewModel
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.NavigationCommand
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val navigator: Navigator) : ViewModel() {

    fun navigateTo(routeToNav: String) {
        navigator.navigate(NavigationCommand.NavigateTo(routeToNav))
    }

}