package com.example.update.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
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
import com.example.update.state.UpdateState
import cu.xetid.dtvc.androidtrainingapp.model.dto.Contact
import cu.xetid.dtvc.androidtrainingapp.ui.component.GenericButtonWithBackgroundComponent
import cu.xetid.dtvc.androidtrainingapp.ui.component.GenericText
import cu.xetid.dtvc.androidtrainingapp.ui.component.GenericTopAppBar
import cu.xetid.dtvc.androidtrainingapp.ui.component.inceritionupdate.CardUpdateIsFavoriteIconsButtonComponent
import cu.xetid.dtvc.androidtrainingapp.ui.component.inceritionupdate.InsertContactTextFieldCity
import cu.xetid.dtvc.androidtrainingapp.ui.component.inceritionupdate.InsertContactTextFieldFirstName
import cu.xetid.dtvc.androidtrainingapp.ui.component.inceritionupdate.InsertContactTextFieldFontNumber
import cu.xetid.dtvc.androidtrainingapp.ui.component.inceritionupdate.InsertContactTextFieldLastName

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

    Scaffold (topBar = {
        GenericTopAppBar(title = "Update", navigateBack = viewModel::navigateBack)
    }){
        UpdateContent(
            paddingValues = it,
            firstName = firstName,
            lastName = lastName,
            fontNumber = fontNumber,
            city = city,
            changeTextValue = viewModel::changeText,
            updateState = updateState,
            updateContact = viewModel::updateContact,
            favorite = favorite,
            onFavorite = viewModel::changeIsFavorite,
            contact = contact,
            isReadyForUpdate = viewModel.isReadyForUpdate()
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
    favorite: Boolean,
    onFavorite: () -> Unit,
    changeTextValue: (String, String, String, String) -> Unit,
    updateState: UpdateState,
    updateContact: () -> Unit,
    contact: Contact,
    isReadyForUpdate:Boolean
) {

    LazyColumn(
        modifier = modifier
            .padding(paddingValues)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        item {
            Row {
                GenericText(
                    textTitle = contact.firstName,
                    textStyle = MaterialTheme.typography.headlineLarge,
                )
                CardUpdateIsFavoriteIconsButtonComponent(
                    onFunction = onFavorite,
                    isFavorite = favorite,
                    imageVectorToPaint = Icons.Filled.Star
                )
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

            if (updateState.error.isNotBlank()) Text(text = updateState.error, color = Color.Red)

        }

    }

}



