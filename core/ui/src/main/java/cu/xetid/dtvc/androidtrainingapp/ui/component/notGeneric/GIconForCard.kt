package cu.xetid.dtvc.androidtrainingapp.ui.component.notGeneric

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import cu.xetid.dtvc.androidtrainingapp.model.dto.Contact
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.routes.update.UpdateRoute


/**
 * Not ready for vi generic
 */
    @Composable
    fun GIconForCard(
        modifier: Modifier = Modifier,
        onFunction: (Contact) -> Unit = {},
        navigateTo: (String) -> Unit = {},
        contact: Contact,
        imageVectorToPaint: ImageVector,
        isColorMutable: Boolean = false
    ) {
        var isButtonEnabled by remember {
            mutableStateOf(true)
        }
        IconButton(
            onClick = {
                isButtonEnabled = !isButtonEnabled
                if (!isColorMutable) onFunction.invoke(contact) else navigateTo.invoke(UpdateRoute.UpdateScreenRoute.route + contact.contactId)
                isButtonEnabled = !isButtonEnabled
            },
            modifier = modifier,
            enabled = isButtonEnabled,
            colors = IconButtonDefaults.iconButtonColors(contentColor = if (contact.favorite && isColorMutable) Color.Yellow else Color.LightGray)
        ) {
            Icon(imageVector = imageVectorToPaint, contentDescription = "Delete")
        }
    }
