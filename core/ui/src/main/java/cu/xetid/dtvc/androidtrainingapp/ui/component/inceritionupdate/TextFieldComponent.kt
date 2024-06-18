package cu.xetid.dtvc.androidtrainingapp.ui.component.inceritionupdate

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType


@Composable
fun CardUpdateIsFavoriteIconsButtonComponent(
    modifier: Modifier = Modifier,
    onFunction: () -> Unit,
    isFavorite: Boolean,
    imageVectorToPaint: ImageVector,
) {
    var isButtonEnabled by remember {
        mutableStateOf(true)
    }
    IconButton(
        onClick = {
            isButtonEnabled = !isButtonEnabled
            onFunction.invoke()
            isButtonEnabled = !isButtonEnabled
        },
        modifier = modifier,
        enabled = isButtonEnabled,
        colors = IconButtonDefaults.iconButtonColors(contentColor = if (isFavorite) Color.Yellow else Color.LightGray)
    ) {
        Icon(imageVector = imageVectorToPaint, contentDescription = "Favorite")
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
        label = { Text(text = valueTextField) })


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
        label = { Text(text = valueTextField) })


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
        label = { Text(text = valueTextField) })

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
        label = { Text(text = valueTextField) }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))


}