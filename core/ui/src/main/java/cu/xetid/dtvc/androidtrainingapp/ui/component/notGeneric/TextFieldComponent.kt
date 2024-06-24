package cu.xetid.dtvc.androidtrainingapp.ui.component.notGeneric

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cu.xetid.dtvc.androidtrainingapp.ui.R
import cu.xetid.dtvc.androidtrainingapp.ui.component.image.GenericImageRemote


@Composable
fun CardPicture(
    modifier: Modifier = Modifier,
    imageRouteToPaint: String
) {
    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        GenericImageRemote(
            urlImageToPaint = imageRouteToPaint,
            loadingResDrawableImage = R.drawable.contacts2,
            errorResDrawableImage = R.drawable.contacts2,
            imageSize = 80
        )
    }
}


@Composable
fun InsertContactTextFieldFirstName(
    firstName: String,
    lastName: String,
    fontNumber: String,
    city: String,
    changeTextValue: (String, String, String, String) -> Unit,
    valueTextField: String
) {

    OutlinedTextField(
        value = firstName,
        onValueChange = { changeTextValue.invoke(it, lastName, city, fontNumber) },
        label = { Text(text = valueTextField) },
        maxLines = 1,
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)

    )


}

@Composable
fun InsertContactTextFieldLastName(
    firstName: String,
    lastName: String,
    fontNumber: String,
    city: String,
    changeTextValue: (String, String, String, String) -> Unit,
    valueTextField: String
) {

    OutlinedTextField(
        value = lastName,
        onValueChange = { changeTextValue.invoke(firstName, it, city, fontNumber) },
        label = { Text(text = valueTextField) },
        maxLines = 1,
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)

    )


}

@Composable
fun InsertContactTextFieldCity(
    firstName: String,
    lastName: String,
    city: String,
    fontNumber: String,
    changeTextValue: (String, String, String, String) -> Unit,
    valueTextField: String
) {

    OutlinedTextField(
        value = city,
        onValueChange = { changeTextValue.invoke(firstName, lastName, it, fontNumber) },
        label = { Text(text = valueTextField) },
        maxLines = 1,
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
    )

}

@Composable
fun InsertContactTextFieldFontNumber(
    firstName: String,
    lastName: String,
    city: String,
    fontNumber: String,
    changeTextValue: (String, String, String, String) -> Unit,
    valueTextField: String
) {

    OutlinedTextField(
        value = fontNumber,
        onValueChange = { changeTextValue.invoke(firstName, lastName, city, it) },
        label = { Text(text = valueTextField) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        maxLines = 1,
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)

    )


}