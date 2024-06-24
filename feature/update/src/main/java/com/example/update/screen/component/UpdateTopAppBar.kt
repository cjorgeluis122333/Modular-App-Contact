package com.example.update.screen.component

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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateTopAppBar(
    title: String,
    navigateBack: () -> Unit = {},
    isNavigationIconEnable: Boolean = true,
    actionsIconsList: List<ImageVector> = emptyList(),
    actionsEventList: List<() -> Unit> = emptyList(),
    enableIsRestarted: List<Boolean> = emptyList(),
    isFavorite: Boolean = false
) {

    var isBackEnable by rememberSaveable { mutableStateOf(true) }
    var isActionEnable by rememberSaveable { mutableStateOf(true) }

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
            overflow = TextOverflow.Ellipsis,
            textDecoration = TextDecoration.None,
            textAlign = TextAlign.Start
        )
    }, navigationIcon = {
        if (isNavigationIconEnable) {
            IconButton(onClick = {
                isBackEnable = !isBackEnable
                navigateBack.invoke()
            }, enabled = isBackEnable, content = {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "go back"
                )
            }
            )
        }
    },


        actions = {
            if (actionsIconsList.isNotEmpty() && actionsEventList.isNotEmpty())
                for (i in actionsIconsList.indices) {
                    IconButton(
                        onClick = {
                            if (enableIsRestarted[i]) {
                                isActionEnable = !isActionEnable
                                actionsEventList[i].invoke()
                                isActionEnable = !isActionEnable
                            } else {
                                isBackEnable = !isBackEnable
                                actionsEventList[i].invoke()
                            }
                        },
                        enabled = if (enableIsRestarted[i]) isActionEnable else isBackEnable,
                    ) {
                        Icon(
                            imageVector = actionsIconsList[i],
                            contentDescription = null,
                            tint = if (i == 1 && isFavorite)
                                MaterialTheme.colorScheme.contentColorFor(MaterialTheme.colorScheme.onPrimaryContainer)
                            else
                                MaterialTheme.colorScheme.contentColorFor(MaterialTheme.colorScheme.primaryContainer)
                        )
                    }
                }
        }
    )


}
