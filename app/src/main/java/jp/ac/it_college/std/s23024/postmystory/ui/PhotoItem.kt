package jp.ac.it_college.std.s23024.postmystory.ui


import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import jp.ac.it_college.std.s23024.postmystory.R

@Composable
fun PhotoItem(
    modifier: Modifier = Modifier,
    photo: String,
    onClick: (String) -> Unit = {}
) {
   AsyncImage(
           model = ImageRequest.Builder(LocalContext.current)
               .data(photo)
               .crossfade(true)
               .diskCachePolicy(CachePolicy.DISABLED)
               .build(),
           contentDescription = null,
           modifier = modifier.clickable { onClick(photo) },
           contentScale = ContentScale.FillWidth,
           placeholder = painterResource(id = R.drawable.now_loading)
       )
}

@Preview
@Composable
fun PhotoItemPreview() {
    PhotoItem(photo = "https://picsum.photos/200/200")
}