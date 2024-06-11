package cu.xetid.dtvc.androidtrainingapp.home.homescreen.viewmodel

import androidx.lifecycle.ViewModel
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.NavigationCommand
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel()
class HelpViewModel @Inject constructor(private val navigator: Navigator) : ViewModel() {

    fun navigateTo(routeToNav: String) {
        navigator.navigate(NavigationCommand.NavigateTo(routeToNav))
    }

}