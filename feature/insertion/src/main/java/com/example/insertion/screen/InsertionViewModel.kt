package com.example.insertion.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cu.xetid.dtvc.androidtrainingapp.domain.usecase.contact.ContactInsertionUsesCase
import cu.xetid.dtvc.androidtrainingapp.model.dto.Contact
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.NavigationCommand
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.Navigator
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.routes.home.HomeRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsertionViewModel @Inject constructor(
    private val navigator: Navigator,
    private val contactInsertionUsesCase: ContactInsertionUsesCase
) : ViewModel() {

    fun insertNewContactAtDataBase() {
        viewModelScope.launch {
            try {
                if (isReadyForInsert()) {
                    contactInsertionUsesCase.invoke(
                        Contact(
                            firstName = _firstName.value,
                            lastName = _lastName.value,
                            city = _city.value,
                            fontNumber = _fontNumber.value
                        )
                    )
                    navigateBack()
                } else {
                    _isError.value = "Complete all text field"
                }

            } catch (e: Exception) {
                _isError.value = e.message.orEmpty()

            }
        }
    }

     fun isReadyForInsert(): Boolean {
        return _firstName.value.isNotBlank() && _fontNumber.value.length == 8
    }

    fun changeText(firstName: String, lastName: String, city: String,fontNumber: String) {
        _firstName.value = firstName
        _lastName.value = lastName
        _city.value = city
        if (fontNumber.length > 8) _isError.value =
            "The font number do not have more than 8 characters"
        else _fontNumber.value = fontNumber

    }

    fun navigateBack() {
        navigator.navigate(NavigationCommand.PopBackstack)
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

    //                       State

    private val _isError: MutableStateFlow<String> =
        MutableStateFlow(value = "")
    val isError: StateFlow<String> = _isError


}