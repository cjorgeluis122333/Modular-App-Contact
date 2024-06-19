package com.example.insertion.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.insertion.state.InsertionState
import cu.xetid.dtvc.androidtrainingapp.ui.component.GenericButtonWithBackgroundComponent
import cu.xetid.dtvc.androidtrainingapp.ui.component.GenericTopAppBar
import cu.xetid.dtvc.androidtrainingapp.ui.component.inceritionupdate.InsertContactTextFieldCity
import cu.xetid.dtvc.androidtrainingapp.ui.component.inceritionupdate.InsertContactTextFieldFirstName
import cu.xetid.dtvc.androidtrainingapp.ui.component.inceritionupdate.InsertContactTextFieldFontNumber
import cu.xetid.dtvc.androidtrainingapp.ui.component.inceritionupdate.InsertContactTextFieldLastName

@Composable
fun InsertionScreen(viewModel: InsertionViewModel = hiltViewModel()) {
    val firstName by viewModel.firstName.collectAsState()
    val lastName by viewModel.lstName.collectAsState()
    val fontNumber by viewModel.fontNumber.collectAsState()
    val city by viewModel.city.collectAsState()
    val insertionState by viewModel.insertionState.collectAsState()

    Scaffold(
        topBar = {
            GenericTopAppBar(
                title = "Create new contact",
                isNavigationIconEnable = true,
                navigateBack = viewModel::navigateBack
            )
        },
    ) {
        InsertionContent(
            paddingValues = it,
            firstName = firstName,
            lastName = lastName,
            fontNumber = fontNumber,
            city = city,
            changeTextValue = viewModel::changeText,
            insertionState = insertionState,
            insertNewContact = viewModel::insertNewContactAtDataBase,
            isReadyForInsert = viewModel.isReadyForInsert()
        )
    }
}


@Composable
fun InsertionContent(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    firstName: String,
    lastName: String,
    fontNumber: String,
    city: String,
    changeTextValue: (String, String, String, String) -> Unit,
    insertionState: InsertionState,
    insertNewContact: () -> Unit,
    isReadyForInsert: Boolean
) {
    // val isSusses = insertionState.susses


    LazyColumn(
        modifier = modifier
            .padding(paddingValues)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {

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
                navigateTo = { insertNewContact.invoke() },
                buttonText = "Create contact",
                isEnable = isReadyForInsert
            )

            if (insertionState.error.isNotBlank()) Text(
                text = insertionState.error,
                color = Color.Red
            )

        }

    }


}

