package com.example.update.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.update.state.UpdateState
import cu.xetid.dtvc.androidtrainingapp.domain.usecase.contact.ContactDeleteUsesCase
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
    private val contactUpdateUsesCase: ContactUpdateUsesCase,
    private val deleteUsesCase: ContactDeleteUsesCase
) : ViewModel() {

    //Function will start with the application in a Launched Effect
    fun getSpecificContact(idContact: Int) {

        viewModelScope.launch {
            changeState(isError = "", isLoading = false)
            try {
                contactSelectByIdUsesCase.invoke(idContact).collect {
                    _contactToUpdate.value = it
                    _city.value = _contactToUpdate.value.city.orEmpty()
                    _fontNumber.value = _contactToUpdate.value.fontNumber
                    _lastName.value = _contactToUpdate.value.lastName.orEmpty()
                    _firstName.value = _contactToUpdate.value.firstName
                    _favorite.value = _contactToUpdate.value.favorite
                }
            } catch (e: Exception) {
                //     changeState(isError = e.message.orEmpty(), isLoading = false)
            }
        }
    }

    //                                                    Events
    fun onUpdateContact() {
        changeState(isError = "", isLoading = true)

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

                if (onIsReadyForUpdate()) {
                    contactUpdateUsesCase(contactTmp)
                    navigateBack()
                } else {
                    changeState(isLoading = false, isError = "Check the text value")
                }


        }
    }

    fun onDeleteContact() {
        viewModelScope.launch {
            changeState(isError = "", isLoading = true)
            deleteUsesCase(_contactToUpdate.value)
            navigateBack()
        }
    }

    fun onIsReadyForUpdate(): Boolean {
        try {
            val tmp = _fontNumber.value.toLong()
            return _firstName.value.isNotBlank() && _fontNumber.value.length == 8

        } catch (e: NumberFormatException) {
            return false
        }

    }

    fun onChangeText(firstName: String, lastName: String, city: String, fontNumber: String) {
        _firstName.value = firstName
        _lastName.value = lastName
        _city.value = city
        if (fontNumber.length > 8)
            changeState(isError = "The font number do not have more than 8 characters")
        else _fontNumber.value = fontNumber


    }

    //Event is change favorite
    fun onChangeIsFavorite() {
        _favorite.value = !_favorite.value
    }

    //                            Navigate
    fun navigateBack() {
        navigator.navigate(NavigationCommand.PopBackstack)
    }

    //                            Change State
    private fun changeState(
        isLoading: Boolean = _updateState.value.isLoading,
        isError: String = _updateState.value.error
    ) {
        _updateState.update {
            it.copy(
                isLoading = isLoading,
                error = isError
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


}