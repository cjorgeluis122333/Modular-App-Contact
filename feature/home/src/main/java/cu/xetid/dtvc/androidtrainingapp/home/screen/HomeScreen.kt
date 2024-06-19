package cu.xetid.dtvc.androidtrainingapp.home.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cu.xetid.dtvc.androidtrainingapp.home.viewmodel.HomeViewModel
import cu.xetid.dtvc.androidtrainingapp.home.screen.component.ContactButtonBar
import cu.xetid.dtvc.androidtrainingapp.home.screen.component.ContactFloatingActionButton
import cu.xetid.dtvc.androidtrainingapp.home.screen.component.GenericContactAccountImage
import cu.xetid.dtvc.androidtrainingapp.home.state.HomeState
import cu.xetid.dtvc.androidtrainingapp.model.dto.Contact
import cu.xetid.dtvc.androidtrainingapp.ui.component.GenericText
import cu.xetid.dtvc.androidtrainingapp.ui.component.GenericTopAppBar
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.routes.update.UpdateRoute

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {

    val stateHome by viewModel.homeUIState.collectAsState()
    val selectAllContactSubscriberAtUI by viewModel.allContactSubscriberAtUI.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            GenericTopAppBar(
                title = "Contact",
                isNavigationIconEnable = false
            )
        },
        bottomBar = {
            ContactButtonBar(navigateTo = viewModel::navigateTo)
        },
        floatingActionButton = { ContactFloatingActionButton(navigateToInsert = viewModel::navigateTo) }) {
        HomeContent(
            paddingValues =  it,
            navigateTo = viewModel::navigateTo,
            onDelete = viewModel::deleteContact,
            stateHome = stateHome,
            modifier = Modifier,
            selectAll = selectAllContactSubscriberAtUI
        )

    }
}

@Composable
fun HomeContent(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    navigateTo: (String) -> Unit,
    onDelete: (Contact) -> Unit,
    stateHome: HomeState,
    selectAll:List<Contact>?
) {
    val size = selectAll?.size ?:0
    LazyColumn(
        modifier = modifier
            .padding(paddingValues)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (!stateHome.loading) {

            items(size) {
                Spacer(modifier = modifier.height(16.dp))
                CardContact(
                    contact = selectAll!![it],
                    navigateTo = navigateTo,
                    onDelete = onDelete,
                )
            }

            item {
                if (stateHome.error.isNotBlank()) Text(text = stateHome.error, color = MaterialTheme.colorScheme.error)
            }
        } else {
            item {
                Box(modifier = modifier.fillParentMaxSize(), contentAlignment = Alignment.Center) {

                    CircularProgressIndicator()

                }
            }
        }
    }
}

@Composable
fun CardContact(
    modifier: Modifier = Modifier,
    contact: Contact,
    navigateTo: (String) -> Unit,
    onDelete: (Contact) -> Unit,
) {
    Card(
        onClick = { navigateTo.invoke(UpdateRoute.UpdateScreenRoute.route + contact.contactId) },
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation()
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(56.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            GenericContactAccountImage(contact = contact, modifier = modifier.weight(0.2f))

            Column(modifier = modifier.weight(0.6f)) {
                GenericText(textTitle = contact.firstName)
                GenericText(textTitle = contact.fontNumber)
            }
            CardHomeIconsButtonComponent(
                onFunction = onDelete,
                imageVectorToPaint = Icons.Filled.Delete,
                contact = contact,
                modifier = modifier.weight(0.1f)
            )
            CardHomeIconsButtonComponent(
                navigateTo = navigateTo,
                imageVectorToPaint = Icons.Filled.Star,
                contact = contact,
                modifier = modifier.weight(0.1f),
                isColorMutable = true
            )

        }
    }
}

@Composable
fun CardHomeIconsButtonComponent(
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

