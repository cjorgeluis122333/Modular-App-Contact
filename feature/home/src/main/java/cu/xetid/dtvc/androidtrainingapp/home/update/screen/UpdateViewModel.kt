package cu.xetid.dtvc.androidtrainingapp.home.update.screen

import androidx.lifecycle.ViewModel
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.NavigationCommand
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UpdateViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {
    fun navigateBack() {
        navigator.navigate(NavigationCommand.PopBackstack)
    }
}