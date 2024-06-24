package cu.xetid.dtvc.androidtrainingapp.ui.component.text

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.sp

@Composable
fun GenericTextTitleDivForALine(
    textTitle: String,
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    maxLines:Int=1
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        HorizontalDivider(modifier = Modifier.weight(1f))
        Text(
            text = textTitle,
            style = textStyle,
            textAlign = TextAlign.Center,
            fontWeight = textStyle.fontWeight,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            letterSpacing = 1.sp,
            fontSize = textStyle.fontSize,
            maxLines = maxLines,
            modifier = Modifier
                .weight(1f)

        )
        HorizontalDivider(modifier = Modifier.weight(1f))
    }
}