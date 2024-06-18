package cu.xetid.dtvc.androidtrainingapp.home.screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.routes.favorite.FavoriteRoute
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.routes.help.HelpRoute
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.routes.home.HomeRoute


@Composable
fun ContactButtonBar(
    navigateTo: (String) -> Unit = {},
    ) {

    BottomAppBar {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            BottomBarItem(
                text = "Favorite",
                imageVector = Icons.Filled.Star,
                navigateTo = { navigateTo.invoke(FavoriteRoute.FavoriteScreenRoute.route) },
                modifier = Modifier.weight(0.33f)
            )


            BottomBarItem(
                text = "Home",
                imageVector = Icons.Filled.Home,
                navigateTo = { navigateTo.invoke(HomeRoute.HomeScreenRoute.route) },
                modifier = Modifier.weight(0.33f)

            )


            BottomBarItem(
                text = "Help",
                imageVector = Icons.Filled.Build,
                navigateTo = { navigateTo.invoke(HelpRoute.HelpScreenRoute.route) },
                modifier = Modifier.weight(0.33f)
            )

        }
    }
}


@Composable
fun BottomBarItem(
    text: String,
    imageVector: ImageVector,
    navigateTo: () -> Unit,
    modifier: Modifier = Modifier
) {
Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center){
    IconButton(onClick = { navigateTo.invoke() },modifier.width(100.dp).height(64.dp)) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(imageVector = imageVector, contentDescription = text)
            Text(text = text)
            Spacer(modifier = modifier.height(8.dp))
        }
    }}
}