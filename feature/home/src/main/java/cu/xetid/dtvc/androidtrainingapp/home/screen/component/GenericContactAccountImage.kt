package cu.xetid.dtvc.androidtrainingapp.home.screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import cu.xetid.dtvc.androidtrainingapp.model.dto.Contact


@Composable
fun GenericContactAccountImage(contact: Contact, modifier: Modifier = Modifier, contactImageSize:Int = 42) {
Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
    Card(
        shape = ShapeDefaults.ExtraLarge,
        modifier = modifier
            .size(contactImageSize.dp)
    ) {
        if (contact.thumbnail?.length!! > 1) {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current).data(data = contact.thumbnail)
                        .apply(block = fun ImageRequest.Builder.() {
                            placeholder(cu.xetid.dtvc.androidtrainingapp.ui.R.drawable.contacts2)
                            error(cu.xetid.dtvc.androidtrainingapp.ui.R.drawable.logo)
                        }).build()
                ),
                contentDescription = "Contact picture",
                modifier = modifier
                    .size(contactImageSize.dp)
                    .clip(shape = MaterialTheme.shapes.extraLarge),
                contentScale = ContentScale.Crop
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.CenterHorizontally)
                    .background(
                        if (contact.fontNumber[1] == '1') Color.Gray
                        else if (contact.fontNumber[1] == '2') Color.Blue
                        else if (contact.fontNumber[1] == '3') Color.Red
                        else if (contact.fontNumber[1] == '4') Color.Yellow
                        else if (contact.fontNumber[1] == '5') Color.Green
                        else if (contact.fontNumber[1] == '6') Color.Black
                        else if (contact.fontNumber[1] == '7') Color.Cyan
                        else if (contact.fontNumber[1] == '8') Color.DarkGray
                        else if (contact.fontNumber[1] == '9') Color.LightGray
                        else Color.Red
                    )
            ) {
                Text(
                    text = contact.firstName[0].toString(),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 8.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp, color = Color.White, fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
}