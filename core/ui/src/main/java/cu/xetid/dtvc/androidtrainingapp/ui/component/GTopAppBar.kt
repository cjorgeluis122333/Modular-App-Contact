package cu.xetid.dtvc.androidtrainingapp.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenericTopAppBar(title: String,navigateBack:()->Unit={},isNavigationIconEnable:Boolean=true) {

    var isBackEnable by rememberSaveable { mutableStateOf(true) }
    TopAppBar(colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        titleContentColor = MaterialTheme.colorScheme.contentColorFor(MaterialTheme.colorScheme.primaryContainer),
        navigationIconContentColor = MaterialTheme.colorScheme.contentColorFor(MaterialTheme.colorScheme.primaryContainer),
        actionIconContentColor = MaterialTheme.colorScheme.contentColorFor(MaterialTheme.colorScheme.primaryContainer)
    ), title = {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.contentColorFor(MaterialTheme.colorScheme.primaryContainer),
            maxLines = 1,
            softWrap = true,
            overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
            textDecoration = androidx.compose.ui.text.style.TextDecoration.None,
            textAlign = androidx.compose.ui.text.style.TextAlign.Start
        )
    }, navigationIcon = {if (isNavigationIconEnable){
        IconButton(onClick = {
            navigateBack.invoke()
            isBackEnable = !isBackEnable
        }, enabled = isBackEnable, content = {
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "go back")
        }
        )}
    },

        actions = {
          //Al icon button
        })


}