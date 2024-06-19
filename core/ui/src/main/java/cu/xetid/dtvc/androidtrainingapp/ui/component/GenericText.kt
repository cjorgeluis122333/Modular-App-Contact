package cu.xetid.dtvc.androidtrainingapp.ui.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun GenericText(
    textTitle: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    maxLine:Int = 1
) {
    Text(
        text = textTitle,
        style = textStyle,
        textAlign = TextAlign.Center,
        fontWeight = textStyle.fontWeight,
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        letterSpacing = 1.sp,
        fontSize = textStyle.fontSize,
        modifier = modifier,
        maxLines = maxLine,

    )
}