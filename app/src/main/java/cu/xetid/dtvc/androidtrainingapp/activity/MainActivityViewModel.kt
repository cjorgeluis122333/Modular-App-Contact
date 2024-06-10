package cu.xetid.dtvc.androidtrainingapp.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cu.xetid.dtvc.androidtrainingapp.domain.usecase.auth.RefreshTokenUseCase
import cu.xetid.dtvc.androidtrainingapp.domain.usecase.idp.SaveIDPUseCase
import cu.xetid.dtvc.androidtrainingapp.model.ResultValue
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.routes.home.HomeRoute
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.routes.login.LogInRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val useCase: RefreshTokenUseCase,
    private val saveIDP: SaveIDPUseCase,
) : ViewModel() {

    var firstScreen = LogInRoute.RootRoute.route
    var isReady: Boolean? = null

    init {
        refreshToken()
        saveIDPs()
    }

    private fun saveIDPs() = viewModelScope.launch {
        saveIDP()
    }

    private fun refreshToken() {
        viewModelScope.launch {
            when (val result = useCase()) {
                is ResultValue.Error -> {
                    firstScreen = LogInRoute.RootRoute.route
                    isReady = true
                }

                is ResultValue.Success -> {
                    result.data.let {
                        firstScreen = if (it) {
                            HomeRoute.RootRoute.route
                        } else {
                            LogInRoute.RootRoute.route
                        }
                    }
                    isReady = true
                }
            }
        }
    }
}
