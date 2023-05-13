package com.example.ktorexample.ktor

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.ktorexample.ui.theme.KtorExampleTheme


@Composable
fun RestFulScreen(data: Picture) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .align(Alignment.Center)
            .padding(20.dp)) {

            Image(
                painter = rememberAsyncImagePainter(data.imageUrl),
                contentDescription = "",
                modifier = Modifier
                    .size(300.dp)
                    .border(1.dp, Color.Gray)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Crop
            )
            Divider(Modifier.padding(10.dp))
            Text("Title: ${data.title}")
            Text("Location: ${data.location}")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KtorExampleTheme {
    }
}