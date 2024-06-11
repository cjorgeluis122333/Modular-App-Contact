package cu.xetid.dtvc.androidtrainingapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun GenericErrorScreenWithRefresh(errorMessages: String, tryAgain: () -> Unit, stateChange:()->Unit) {
    var isButtonEnabled by remember { mutableStateOf(true) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = errorMessages,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = TextStyle(color = Color.Red),
            fontWeight = MaterialTheme.typography.headlineLarge.fontWeight,
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            textAlign = MaterialTheme.typography.headlineLarge.textAlign
        )

        TextButton(
            onClick = {
                isButtonEnabled = false
                stateChange.invoke()
                tryAgain.invoke()
                isButtonEnabled = true
            },
            elevation = ButtonDefaults.buttonElevation(3.dp),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
        ) {

            Text(
                text = "Try Again",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = MaterialTheme.typography.bodyLarge.fontWeight
            )

        }

    }
}