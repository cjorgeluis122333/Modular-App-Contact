package cu.xetid.dtvc.androidtrainingapp.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cu.xetid.dtvc.androidtrainingapp.domain.usecase.contact.ContactDeleteUsesCase
import cu.xetid.dtvc.androidtrainingapp.domain.usecase.contact.ContactSelectAllUsesCase
import cu.xetid.dtvc.androidtrainingapp.domain.usecase.contact.ContactSelectFavoriteUsesCase
import cu.xetid.dtvc.androidtrainingapp.domain.usecase.contact.proyection.ContactSearchLocationUsesCase
import cu.xetid.dtvc.androidtrainingapp.domain.usecase.contact.proyection.ContactSearchNameUsesCase
import cu.xetid.dtvc.androidtrainingapp.domain.usecase.contact.proyection.ContactSearchPictureUsesCase
import cu.xetid.dtvc.androidtrainingapp.model.dto.Contact
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.NavigationCommand
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val navigator: Navigator,
    private val contactSelectAllUsesCase: ContactSelectAllUsesCase,
    private val contactDeleteUsesCase: ContactDeleteUsesCase,
    private val selectFavoriteContactUsesCase:ContactSelectFavoriteUsesCase,
    private val contactSearchLocationUsesCase: ContactSearchLocationUsesCase,
    private val contactSearchPictureUsesCase: ContactSearchPictureUsesCase,
    private val contactSearchNameUsesCase: ContactSearchNameUsesCase
) : ViewModel() {


    //------------------------------------------------Contact event

    fun readAllContact() {
        viewModelScope.launch {
            try {
                contactSelectAllUsesCase.invoke().collect {
                    _isLoading.value = true
                    _selectAll.value = it
                    _isLoading.value = false
                }

            } catch (e: Exception) {
                _isError.value = e.message.orEmpty()
            }
        }
    }

    fun selectFavoriteContact() {
        viewModelScope.launch {
            try {
                selectFavoriteContactUsesCase.invoke().collect {
                    _isLoading.value = true
                    _selectFavorite.value = it
                    _isLoading.value = false
                }

            } catch (e: Exception) {
                _isError.value = e.message.orEmpty()
            }
        }
    }


    fun deleteContact(contactToDelete: Contact) {
        viewModelScope.launch {
            try {
                contactDeleteUsesCase.invoke(contactToDelete)
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

    private val _selectFavorite: MutableStateFlow<List<Contact>> =
        MutableStateFlow(value = emptyList())
    val selectFavorite: StateFlow<List<Contact>> = _selectFavorite


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