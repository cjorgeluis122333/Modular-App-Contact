package com.example.update.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.update.state.UpdateState
import cu.xetid.dtvc.androidtrainingapp.domain.usecase.contact.ContactSelectByIdUsesCase
import cu.xetid.dtvc.androidtrainingapp.domain.usecase.contact.ContactUpdateUsesCase
import cu.xetid.dtvc.androidtrainingapp.model.dto.Contact
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.NavigationCommand
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateViewModel @Inject constructor(
    private val navigator: Navigator,
    private val contactSelectByIdUsesCase: ContactSelectByIdUsesCase,
    private val contactUpdateUsesCase: ContactUpdateUsesCase
) : ViewModel() {

    //Function will start with the application
    fun getSpecificContact(idContact: Int) {

        viewModelScope.launch {
            try {

                contactSelectByIdUsesCase.invoke(idContact).collect {
                    _contactToUpdate.value = it
                    _city.value = _contactToUpdate.value.city.orEmpty()
                    _fontNumber.value = _contactToUpdate.value.fontNumber
                    _lastName.value = _contactToUpdate.value.lastName.orEmpty()
                    _firstName.value = _contactToUpdate.value.firstName
                    _favorite.value = _contactToUpdate.value.favorite
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                _isError.value = e.message.orEmpty()
            }
        }
    }

    //Event update
    fun updateContact() {
        val contactTmp = Contact(
            contactId = _contactToUpdate.value.contactId,
            firstName = _firstName.value,
            lastName = _lastName.value,
            city = _city.value,
            fontNumber = _fontNumber.value,
            thumbnail = _contactToUpdate.value.thumbnail,
            favorite = _favorite.value
        )
        viewModelScope.launch {
            if (isReadyForUpdate()) {
                contactUpdateUsesCase.invoke(contactTmp)
                navigateBack()
            } else {
                _isError.value = "Check the text value"
            }
        }
    }

    fun isReadyForUpdate(): Boolean {
        return _firstName.value.isNotBlank() && _fontNumber.value.length == 8

    }

    fun changeText(firstName: String, lastName: String, city: String, fontNumber: String) {
        _firstName.value = firstName
        _lastName.value = lastName
        _city.value = city
        if (fontNumber.length > 8) _isError.value =
            "The font number do not have more than 8 characters"
        else _fontNumber.value = fontNumber


    }

    fun changeIsFavorite() {
        _favorite.value = !_favorite.value
    }

    //                            Navigate
    fun navigateBack() {
        navigator.navigate(NavigationCommand.PopBackstack)
    }

    //                            Change State
    private fun changeState(isLoading: Boolean, isError: String) {
        _updateState.update {
            it.copy(
                error = isError,
                isLoading = isLoading
            )
        }
    }

    //                            All variables
    private val _firstName: MutableStateFlow<String> = MutableStateFlow("")
    val firstName: StateFlow<String> = _firstName

    private var _lastName: MutableStateFlow<String> = MutableStateFlow("")
    val lstName: StateFlow<String> = _lastName

    private var _fontNumber: MutableStateFlow<String> = MutableStateFlow("")
    val fontNumber: StateFlow<String> = _fontNumber

    private var _city: MutableStateFlow<String> = MutableStateFlow("")
    val city: StateFlow<String> = _city

    private var _favorite: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val factory: StateFlow<Boolean> = _favorite


    //                        All state

    private val _updateState: MutableStateFlow<UpdateState> = MutableStateFlow(UpdateState())
    val updateState: StateFlow<UpdateState> = _updateState


    private val _contactToUpdate: MutableStateFlow<Contact> = MutableStateFlow(Contact())
    val contactToUpdate: StateFlow<Contact> = _contactToUpdate

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isError: MutableStateFlow<String> = MutableStateFlow("")
    val isError: StateFlow<String> = _isError


}