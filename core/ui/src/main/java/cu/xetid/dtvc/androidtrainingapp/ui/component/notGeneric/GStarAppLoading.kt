package cu.xetid.dtvc.androidtrainingapp.ui.component.notGeneric

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cu.xetid.dtvc.androidtrainingapp.ui.R
import cu.xetid.dtvc.androidtrainingapp.ui.component.text.GenericTextTitleDivForALine

@Composable
fun GStarAppLoading(imageToPainter:Int = R.drawable.contacts2) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = imageToPainter),
            contentDescription = "Logo",
            modifier = Modifier.size(200.dp)
        )
        GenericTextTitleDivForALine(textTitle ="Welcome" , textStyle = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(64.dp))
        LinearProgressIndicator()
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Loading...")


    }

}