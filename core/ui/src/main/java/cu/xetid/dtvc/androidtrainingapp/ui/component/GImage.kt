package cu.xetid.dtvc.androidtrainingapp.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun GenericImage(imageRotDirection: Int, contentDescription: String = "Header Image") {

    Image(
        painter = painterResource(id = imageRotDirection),
        contentDescription = contentDescription,
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(bottomEnd = 40.dp, bottomStart = 40.dp)),
        contentScale = ContentScale.Crop
    )


}