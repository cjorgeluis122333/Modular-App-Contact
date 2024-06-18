package cu.xetid.dtvc.androidtrainingapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun GenericButtonWithBackgroundComponent(
    navigateTo: () -> Unit,
    buttonText: String,
    maxLinesAtButtonText: Int = 1,
    buttonHeight: Int = 50,
    isEnable:Boolean
) {


    var isButtonEnabled by rememberSaveable { mutableStateOf(true) }

    OutlinedButton(
        onClick = {
            if (isEnable) {
                isButtonEnabled = !isButtonEnabled
                navigateTo.invoke()
            }else{
                navigateTo.invoke()
            }
        },
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(buttonHeight.dp)
            .clip(shape = MaterialTheme.shapes.extraLarge)
            .background(color = MaterialTheme.colorScheme.primary),
        colors = ButtonDefaults.outlinedButtonColors(),
        enabled = isButtonEnabled,
        shape = MaterialTheme.shapes.extraLarge,
        border = null,
        contentPadding = PaddingValues(0.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp
        )

    ) {
        Text(
            text = buttonText,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            fontWeight = FontWeight.Bold,
            letterSpacing = MaterialTheme.typography.bodyLarge.letterSpacing,
            fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
            lineHeight = MaterialTheme.typography.bodyLarge.lineHeight,
            maxLines = maxLinesAtButtonText,
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.primary)
                .align(Alignment.CenterVertically), textAlign = TextAlign.Center

        )

    }


}