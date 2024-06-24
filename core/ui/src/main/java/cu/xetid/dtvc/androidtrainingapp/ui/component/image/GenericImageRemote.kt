package cu.xetid.dtvc.androidtrainingapp.ui.component.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun GenericImageRemote (modifier: Modifier = Modifier, imageSize:Int = 42, urlImageToPaint:String, loadingResDrawableImage:Int, errorResDrawableImage:Int){
    Card(
        shape = MaterialTheme.shapes.extraLarge,
        modifier = modifier
            .size(imageSize.dp)
    ) {

            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current).data(data =urlImageToPaint)
                        .apply(block = fun ImageRequest.Builder.() {
                            placeholder(loadingResDrawableImage)
                            error(errorResDrawableImage)
                        }).build()
                ),
                contentDescription = urlImageToPaint,
                modifier = modifier.size(imageSize.dp)
                    .clip(shape = MaterialTheme.shapes.extraLarge),
                contentScale = ContentScale.Crop
            )
        }
}