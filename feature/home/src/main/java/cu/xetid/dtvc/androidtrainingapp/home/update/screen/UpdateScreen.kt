package cu.xetid.dtvc.androidtrainingapp.home.update.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun UpdateScreen(viewModel: UpdateViewModel = hiltViewModel(), userId: String) {
    Scaffold {
        UpdateContent(it, userId = userId, navigateBack = viewModel::navigateBack)
    }
}

@Composable
fun UpdateContent(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    userId: String,
    navigateBack: () -> Unit
) {

    LazyColumn(
        modifier = modifier
            .padding(paddingValues)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item {

            Text(text = "User id is: $userId")
            Button(onClick = { navigateBack.invoke() }) {

            }
        }

    }

}
