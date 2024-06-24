package cu.xetid.dtvc.androidtrainingapp.ui.component.text

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun GenericRowFor6Text(
    oneText: String,
    twoText: String,
    threeText: String,
    fourthText: String,
    fiveText: String,
    sixText: String,
    isForHeader: Boolean = false
) {

    Row(modifier = Modifier.fillMaxWidth()) {

        Text(
            text = oneText,
            style = if (isForHeader) MaterialTheme.typography.bodyMedium else MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            maxLines = 3,
            modifier = Modifier
                .weight(16.82f)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Text(
            text = if (isForHeader)twoText else twoText.substring(0,4),
            style = if (isForHeader) MaterialTheme.typography.bodyMedium else MaterialTheme.typography.bodySmall,
            color = if (twoText == "DISPONIBLE") Color.Green else if(isForHeader) MaterialTheme.colorScheme.onPrimaryContainer else Color.Red,
            maxLines = 3,
            modifier = Modifier
                .weight(16.50f)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Text(
            text = if (isForHeader) threeText else threeText.substring(0,4),
            style = if (isForHeader) MaterialTheme.typography.bodyMedium else MaterialTheme.typography.bodySmall,
            color = if (threeText == "DISPONIBLE") Color.Green else if(isForHeader) MaterialTheme.colorScheme.onPrimaryContainer else Color.Red,
            maxLines = 3,
            modifier = Modifier
                .weight(16.50f)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Text(
            text = if (isForHeader) fourthText else fourthText.substring(0,4),
            style = if (isForHeader) MaterialTheme.typography.bodyMedium else MaterialTheme.typography.bodySmall,
            color = if (fourthText == "DISPONIBLE") Color.Green else if(isForHeader) MaterialTheme.colorScheme.onPrimaryContainer else Color.Red,
            maxLines = 3,
            modifier = Modifier
                .weight(17.04f)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Text(
            text = if (isForHeader)fiveText else fiveText.substring(0,4),
            style = if (isForHeader) MaterialTheme.typography.bodyMedium else MaterialTheme.typography.bodySmall,
            color = if (fiveText == "DISPONIBLE") Color.Green else if(isForHeader) MaterialTheme.colorScheme.onPrimaryContainer else Color.Red,
            maxLines = 3,
            modifier = Modifier
                .weight(16.50f)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Text(
            text = if (isForHeader)sixText else sixText.substring(0,4),
            style = if (isForHeader) MaterialTheme.typography.bodyMedium else MaterialTheme.typography.bodySmall,
            color = if (sixText == "DISPONIBLE") Color.Green else if(isForHeader) MaterialTheme.colorScheme.onPrimaryContainer else Color.Red,
            maxLines = 3,
            modifier = Modifier
                .weight(16.60f)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}