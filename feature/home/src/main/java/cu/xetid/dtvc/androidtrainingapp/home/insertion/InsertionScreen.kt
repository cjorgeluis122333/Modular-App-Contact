package cu.xetid.dtvc.androidtrainingapp.home.insertion

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
fun InsertionScreen(viewModel: InsertionViewModel = hiltViewModel()) {
    Scaffold {
        InsertionContent(paddingValues = it, navigateBack = viewModel::navigateBack)
    }
}

@Composable
fun InsertionContent(paddingValues: PaddingValues, modifier: Modifier = Modifier,navigateBack:()->Unit) {

    LazyColumn(
        modifier = modifier
            .padding(paddingValues)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
item {

    Button(onClick = { navigateBack.invoke()}) {
        Text(text = "Come to Home Screen")
    }
}

    }


}
