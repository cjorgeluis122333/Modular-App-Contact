package cu.xetid.dtvc.androidtrainingapp.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cu.xetid.dtvc.androidtrainingapp.domain.usecase.contact.ContactDeleteUsesCase
import cu.xetid.dtvc.androidtrainingapp.domain.usecase.contact.ContactInsertionUsesCase
import cu.xetid.dtvc.androidtrainingapp.domain.usecase.contact.ContactSelectAllUsesCase
import cu.xetid.dtvc.androidtrainingapp.domain.usecase.contact.ContactSelectFavoriteUsesCase
import cu.xetid.dtvc.androidtrainingapp.domain.usecase.contact.proyection.ContactSearchLocationUsesCase
import cu.xetid.dtvc.androidtrainingapp.domain.usecase.contact.proyection.ContactSearchNameUsesCase
import cu.xetid.dtvc.androidtrainingapp.domain.usecase.contact.proyection.ContactSearchPictureUsesCase
import cu.xetid.dtvc.androidtrainingapp.home.state.HomeState
import cu.xetid.dtvc.androidtrainingapp.model.ResultValue
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
import kotlin.random.Random

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val navigator: Navigator,
    private val contactDeleteUsesCase: ContactDeleteUsesCase,
    contactSelectAllUsesCase: ContactSelectAllUsesCase,
    selectFavoriteContactUsesCase: ContactSelectFavoriteUsesCase,
    private val contactSearchLocationUsesCase: ContactSearchLocationUsesCase,
    private val contactSearchPictureUsesCase: ContactSearchPictureUsesCase,
    private val contactSearchNameUsesCase: ContactSearchNameUsesCase,
    private val contactInsertionUsesCase: ContactInsertionUsesCase
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

    fun createRemoteContact() {
        viewModelScope.launch {
            changeState(true, "")
            when (val resultName = contactSearchNameUsesCase()) {
                is ResultValue.Error -> changeState(false, resultName.exception.message.orEmpty())
                is ResultValue.Success -> {

                    when (val resultPicture = contactSearchPictureUsesCase()) {
                        is ResultValue.Error ->
                            changeState(false, resultPicture.exception.message.orEmpty())

                        is ResultValue.Success -> {

                            when (val resultLocation = contactSearchLocationUsesCase()) {
                                is ResultValue.Error ->
                                    changeState(false, resultLocation.exception.message.orEmpty())

                                is ResultValue.Success -> {

                                    contactInsertionUsesCase(
                                        Contact(
                                            firstName = resultName.data.first,
                                            lastName = resultName.data.last,
                                            city = resultLocation.data.city,
                                            thumbnail = resultPicture.data.thumbnail,
                                            favorite = false,
                                            fontNumber = Random.nextInt(10000000, 99999999)
                                                .toString()
                                        )
                                    )

                                    changeState(false, "")

                                }
                            }
                        }
                    }
                }
            }//End all when

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