package cu.xetid.dtvc.androidtrainingapp.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cu.xetid.dtvc.androidtrainingapp.model.ResultValue
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.routes.home.HomeRoute
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.routes.login.LogInRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(

) : ViewModel() {

    var firstScreen = LogInRoute.RootRoute.route
    var isReady: Boolean? = null


}
