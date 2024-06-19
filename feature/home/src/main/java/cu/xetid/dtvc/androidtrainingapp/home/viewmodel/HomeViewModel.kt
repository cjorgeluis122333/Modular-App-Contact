package cu.xetid.dtvc.androidtrainingapp.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cu.xetid.dtvc.androidtrainingapp.domain.usecase.contact.ContactDeleteUsesCase
import cu.xetid.dtvc.androidtrainingapp.domain.usecase.contact.ContactSelectAllUsesCase
import cu.xetid.dtvc.androidtrainingapp.domain.usecase.contact.ContactSelectFavoriteUsesCase
import cu.xetid.dtvc.androidtrainingapp.domain.usecase.contact.proyection.ContactSearchLocationUsesCase
import cu.xetid.dtvc.androidtrainingapp.domain.usecase.contact.proyection.ContactSearchNameUsesCase
import cu.xetid.dtvc.androidtrainingapp.domain.usecase.contact.proyection.ContactSearchPictureUsesCase
import cu.xetid.dtvc.androidtrainingapp.home.state.HomeState
import cu.xetid.dtvc.androidtrainingapp.model.dto.Contact
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.NavigationCommand
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val navigator: Navigator,
    private val contactSelectAllUsesCase: ContactSelectAllUsesCase,
    private val contactDeleteUsesCase: ContactDeleteUsesCase,
    private val selectFavoriteContactUsesCase: ContactSelectFavoriteUsesCase,
    private val contactSearchLocationUsesCase: ContactSearchLocationUsesCase,
    private val contactSearchPictureUsesCase: ContactSearchPictureUsesCase,
    private val contactSearchNameUsesCase: ContactSearchNameUsesCase
) : ViewModel() {


    //------------------------------------------------Contact event


    fun deleteContact(contactToDelete: Contact) {
        viewModelScope.launch {
            try {
                changeState(true, "")
                contactDeleteUsesCase.invoke(contactToDelete)
                changeState(false, "")
            } catch (e: Exception) {
                changeState(false, e.message.orEmpty())
            }
        }
    }

    private fun changeState(isLoading: Boolean, onError: String) {
        _homeUiState.update {
            it.copy(
                loading = isLoading,
                error = onError
            )
        }
    }


// ---------------------------------------------------Navigation control

    fun navigateTo(route: String) {
        navigator.navigate(NavigationCommand.NavigateTo(route))
    }

// ---------------------------------------------------Variables control
    /**
     * Load the contacts when start the view is not net a init or LaunchEffect
     */

    val allContactSubscriberAtUI = contactSelectAllUsesCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    /**
     * Load the contacts when start the view is not net a init or LaunchEffect
     */

    val allFavoriteContactSubscriberAtUI = selectFavoriteContactUsesCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

// ---------------------------------------------------state control
    private val _homeUiState: MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
    val homeUIState: StateFlow<HomeState> = _homeUiState




}