package cu.xetid.dtvc.androidtrainingapp.ui.component.colums

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun GenericRowForTwoText(startText: String, endText: String,maxLinesStartText:Int=1,maxLinesEndText:Int=1) {

    Row(modifier = Modifier.fillMaxWidth()) {

        Text(
            text = startText,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            maxLines = maxLinesStartText,
            modifier = Modifier.weight(0.45f)
        )
        Text(
            text = endText,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            maxLines = maxLinesEndText,
            modifier = Modifier.weight(0.55f)
        )
    }
}