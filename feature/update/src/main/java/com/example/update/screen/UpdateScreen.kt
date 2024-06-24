package com.example.update.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.update.screen.component.GenericContactAccountImage
import com.example.update.screen.component.UpdateTopAppBar
import com.example.update.state.UpdateState
import cu.xetid.dtvc.androidtrainingapp.model.dto.Contact
import cu.xetid.dtvc.androidtrainingapp.ui.component.button.GenericButtonWithBackgroundComponent
import cu.xetid.dtvc.androidtrainingapp.ui.component.notGeneric.CardPicture
import cu.xetid.dtvc.androidtrainingapp.ui.component.notGeneric.InsertContactTextFieldCity
import cu.xetid.dtvc.androidtrainingapp.ui.component.notGeneric.InsertContactTextFieldFirstName
import cu.xetid.dtvc.androidtrainingapp.ui.component.notGeneric.InsertContactTextFieldFontNumber
import cu.xetid.dtvc.androidtrainingapp.ui.component.notGeneric.InsertContactTextFieldLastName
import cu.xetid.dtvc.androidtrainingapp.ui.component.text.GenericText
import cu.xetid.dtvc.androidtrainingapp.ui.component.text.GenericTextTitleDivForALine

@Composable
fun UpdateScreen(viewModel: UpdateViewModel = hiltViewModel(), userId: Int) {
    LaunchedEffect(Unit) {
        viewModel.getSpecificContact(userId)
    }
    val firstName by viewModel.firstName.collectAsState()
    val lastName by viewModel.lstName.collectAsState()
    val fontNumber by viewModel.fontNumber.collectAsState()
    val city by viewModel.city.collectAsState()
    val contact by viewModel.contactToUpdate.collectAsState()
    val favorite by viewModel.factory.collectAsState()
    val updateState by viewModel.updateState.collectAsState()

    Scaffold(topBar = {
        UpdateTopAppBar(
            title = "Update",
            navigateBack = viewModel::navigateBack,
            actionsIconsList = listOf(Icons.Filled.Delete, Icons.Filled.Star),
            actionsEventList = listOf(viewModel::onDeleteContact, viewModel::onChangeIsFavorite),
            enableIsRestarted = listOf(false, true),
            isFavorite = favorite
        )
    }) {
        UpdateContent(
            paddingValues = it,
            firstName = firstName,
            lastName = lastName,
            fontNumber = fontNumber,
            city = city,
            changeTextValue = viewModel::onChangeText,
            updateState = updateState,
            updateContact = viewModel::onUpdateContact,
            contact = contact,
            isReadyForUpdate = viewModel.onIsReadyForUpdate()
        )
    }
}

@Composable
fun UpdateContent(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    firstName: String,
    lastName: String,
    fontNumber: String,
    city: String,
    changeTextValue: (String, String, String, String) -> Unit,
    updateState: UpdateState,
    updateContact: () -> Unit,
    contact: Contact,
    isReadyForUpdate: Boolean
) {

    LazyColumn(
        modifier = modifier
            .padding(paddingValues)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        println("EL CONTACTO ES $contact")
        if (!updateState.isLoading) {
            item {

                if (!contact.thumbnail.isNullOrBlank()) {
                    Column(
                        modifier = modifier.fillParentMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CardPicture(imageRouteToPaint = contact.thumbnail ?: "")
                        Spacer(modifier = modifier.height(16.dp))
                        GenericTextTitleDivForALine(
                            textTitle = contact.firstName,
                            textStyle = MaterialTheme.typography.headlineLarge,
                        )
                        Spacer(modifier = modifier.height(32.dp))

                    }
                }//NOTA: Si no se pone que no sea "" la vista se crea antes de la ejecucion del Launched Effect
                else if (contact.firstName.isNotBlank()){
                    Column(
                        modifier = modifier.fillParentMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        GenericContactAccountImage(
                            contact = contact,
                        )
                        Spacer(modifier = modifier.height(16.dp))
                        GenericTextTitleDivForALine(
                            textTitle = contact.firstName,
                            textStyle = MaterialTheme.typography.headlineLarge,
                        )
                        Spacer(modifier = modifier.height(32.dp))

                    }
                }

                InsertContactTextFieldFirstName(
                    changeTextValue = changeTextValue,
                    valueTextField = "First Name",
                    firstName = firstName,
                    lastName = lastName,
                    fontNumber = fontNumber,
                    city = city,
                )
                InsertContactTextFieldLastName(
                    firstName = firstName,
                    lastName = lastName,
                    fontNumber = fontNumber,
                    city = city,
                    changeTextValue = changeTextValue,
                    valueTextField = "Last Name (Optional)"
                )
                InsertContactTextFieldFontNumber(
                    firstName = firstName,
                    lastName = lastName,
                    fontNumber = fontNumber,
                    city = city,
                    changeTextValue = changeTextValue,
                    valueTextField = "Font Number"
                )

                InsertContactTextFieldCity(
                    firstName = firstName,
                    lastName = lastName,
                    city = city,
                    fontNumber = fontNumber,
                    changeTextValue = changeTextValue,
                    valueTextField = "City (Optional)"
                )

                Spacer(modifier = Modifier.height(16.dp))

                GenericButtonWithBackgroundComponent(
                    navigateTo = { updateContact.invoke() },
                    buttonText = "Update contact",
                    isEnable = isReadyForUpdate
                )

                if (updateState.error.isNotBlank()) Text(
                    text = updateState.error,
                    color = Color.Red
                )

            }

        } else {
            item {
                Box(modifier = modifier.fillParentMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}



