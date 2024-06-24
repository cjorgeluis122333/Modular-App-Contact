package cu.xetid.dtvc.androidtrainingapp.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource


@Composable
fun GenericDropDownMenu(
    iconList: List<Int> = listOf(),
    textList: List<String> = listOf(),
    eventList: List<() -> Unit> = emptyList(),
) {

    var isExpanded by remember { mutableStateOf(false) }

    IconButton(onClick = {
        isExpanded = true
    }) {
        Icon(
            imageVector = Icons.Default.MoreVert, contentDescription = "Open Menu"
        )
    }


    DropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {

        for (i in iconList.indices) {
            DropdownMenuItem(trailingIcon = {
                Icon(painter = painterResource(id = iconList[i]), contentDescription = "item") },
                text = { Text(
                    text = textList[i],
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                ) },
                onClick = {
                eventList[i].invoke()
                isExpanded = false
            }
            )//End DropdownMenuItem
        }//End for
    }//End DropdownMenu
}