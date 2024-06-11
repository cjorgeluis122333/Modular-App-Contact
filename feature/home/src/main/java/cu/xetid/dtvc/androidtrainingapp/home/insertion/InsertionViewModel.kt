package cu.xetid.dtvc.androidtrainingapp.home.insertion

import androidx.lifecycle.ViewModel
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.NavigationCommand
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class InsertionViewModel @Inject constructor(
    private val navigator: Navigator
):ViewModel() {


    fun navigateBack(){
        navigator.navigate(NavigationCommand.PopBackstack)
    }

}