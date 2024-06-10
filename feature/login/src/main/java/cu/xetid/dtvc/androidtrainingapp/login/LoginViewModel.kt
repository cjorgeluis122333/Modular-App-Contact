package cu.xetid.dtvc.androidtrainingapp.login

import androidx.lifecycle.ViewModel
import cu.xetid.dtvc.androidtrainingapp.domain.usecase.auth.SingUseCase
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.NavigationCommand
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val navigator: Navigator,
    private val singUseCase: SingUseCase,
) : ViewModel() {

    fun navigateTo(route: String) {
        navigator.navigate(
            NavigationCommand.NavigateTo(
                route
            )
        )
    }



}