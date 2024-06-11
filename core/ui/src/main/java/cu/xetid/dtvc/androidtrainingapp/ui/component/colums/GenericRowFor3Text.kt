package cu.xetid.dtvc.androidtrainingapp.ui.component.colums

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun GenericRowFor3Text(
    startText: String,
    centerText: String,
    endText: String,
    maxLinesStartText: Int = 1,
    isForHeader: Boolean = false
) {

    Row(modifier = Modifier.fillMaxWidth()) {

        Text(
            text = startText,
            style = if (isForHeader) MaterialTheme.typography.bodyLarge else MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            maxLines = maxLinesStartText,
            modifier = Modifier
                .weight(0.33f)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Text(
            text = centerText,
            style = if (isForHeader) MaterialTheme.typography.bodyLarge else MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            maxLines = 2,
            modifier = Modifier
                .weight(0.33f)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Text(
            text = endText,
            style = if (isForHeader) MaterialTheme.typography.bodyLarge else MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            maxLines = 2,
            modifier = Modifier
                .weight(0.33f)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}