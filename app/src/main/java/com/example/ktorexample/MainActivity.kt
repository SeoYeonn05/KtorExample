package com.example.ktorexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.ktorexample.ktor.Picture
import com.example.ktorexample.ktor.RestFulScreen
import com.example.ktorexample.ktor.RestFulViewModel
import com.example.ktorexample.ui.theme.KtorExampleTheme

class MainActivity : AppCompatActivity() {
    private val viewModel: RestFulViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KtorExampleTheme {
                val data by viewModel.pictureData.collectAsState()

                RestFulScreen(data)
            }
        }
    }
    override fun onResume() {
        super.onResume()
    }
}

@Composable
fun ImageButton(text: String, onClickAction: () -> Unit){
    return TextButton(
        onClick = onClickAction
    ) {
        Text("$text")
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KtorExampleTheme {
    }
}