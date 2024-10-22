package jp.ac.it_college.std.s23024.postmystory

import android.os.Bundle
import android.provider.ContactsContract.Contacts.Photo
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import jp.ac.it_college.std.s23024.postmystory.model.Message
import jp.ac.it_college.std.s23024.postmystory.ui.CaptionScreen
import jp.ac.it_college.std.s23024.postmystory.ui.ListScreen
import jp.ac.it_college.std.s23024.postmystory.ui.PhotoGridScreen
import jp.ac.it_college.std.s23024.postmystory.ui.theme.PostMyStoryTheme

class MainActivity : ComponentActivity() {
    enum class Scene {
        LIST, PHOTOS, CAPTION
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PostMyStoryTheme {
                val messages = remember { mutableStateListOf<Message>() }
                var scene by remember { mutableStateOf(Scene.LIST) }
                var selectUrl by remember { mutableStateOf("") }
                var caption by remember { mutableStateOf("") }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        when (scene) {
                            Scene.LIST -> {
                                ListScreen(messages = messages, onClick = {
                                    scene = Scene.PHOTOS
                                })
                            }

                            Scene.PHOTOS -> {
                                PhotoGridScreen(onClick = { url ->
                                    scene = Scene.CAPTION
                                    selectUrl = url
                                })
                            }

                            Scene.CAPTION -> {
                                CaptionScreen(selectUrl = selectUrl, onClick = {
                                    messages.add(
                                        index = 0, element = Message(
                                            image = selectUrl, caption = caption, nice = 0
                                        )
                                    )
                                    scene = Scene.LIST
                                }, onChange = { newText ->
                                    caption = newText
                                })
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CoilTest(modifier: Modifier = Modifier) {
    AsyncImage(
        model = "https://developer.android.com/static/images/brand/android-head_flat.png",
        contentDescription = null,
    )
}