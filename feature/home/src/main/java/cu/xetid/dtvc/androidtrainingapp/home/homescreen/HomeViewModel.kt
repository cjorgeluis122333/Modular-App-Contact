package cu.xetid.dtvc.androidtrainingapp.home.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cu.xetid.dtvc.androidtrainingapp.domain.usecase.contact.ContactDeleteUsesCase
import cu.xetid.dtvc.androidtrainingapp.domain.usecase.contact.ContactGetAllUsesCase
import cu.xetid.dtvc.androidtrainingapp.domain.usecase.contact.proyection.ContactSearchLocationUsesCase
import cu.xetid.dtvc.androidtrainingapp.domain.usecase.contact.proyection.ContactSearchNameUsesCase
import cu.xetid.dtvc.androidtrainingapp.domain.usecase.contact.proyection.ContactSearchPictureUsesCase
import cu.xetid.dtvc.androidtrainingapp.model.dto.Contact
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.NavigationCommand
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val navigator: Navigator,
    private val contactGetAllUsesCase: ContactGetAllUsesCase,
    private val contactDeleteUsesCase: ContactDeleteUsesCase,
    private val contactSearchLocationUsesCase: ContactSearchLocationUsesCase,
    private val contactSearchPictureUsesCase: ContactSearchPictureUsesCase,
    private val contactSearchNameUsesCase: ContactSearchNameUsesCase
) : ViewModel() {

    init {
        viewModelScope.launch {
            try {
                contactGetAllUsesCase.invoke().collect {
                    _selectAll.value = it
                    delay(2000)
                    _isLoading.value = false
                }

            } catch (e: Exception) {
                _isError.value = e.message.orEmpty()
            }
        }
    }

    //------------------------------------------------Contact event

    fun deleteContact(contactToDelete: Contact) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                contactDeleteUsesCase.invoke(contactToDelete)
                _isLoading.value = false
            } catch (e: Exception) {
                _isError.value = e.message.orEmpty()
            }
        }
    }



// ---------------------------------------------------Navigation control

    fun navigateTo(route: String) {
        navigator.navigate(NavigationCommand.NavigateTo(route))
    }

// ---------------------------------------------------Variables control


    private val _selectAll: MutableStateFlow<List<Contact>> =
        MutableStateFlow(value = emptyList())
    val selectAll: StateFlow<List<Contact>> = _selectAll

    private val _isLoading: MutableStateFlow<Boolean> =
        MutableStateFlow(value = true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isError: MutableStateFlow<String> =
        MutableStateFlow(value = "")
    val isError: StateFlow<String> = _isError


}