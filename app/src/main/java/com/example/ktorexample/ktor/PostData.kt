package com.example.ktorexample.ktor

import kotlinx.serialization.Serializable

// json 결과 처리
@Serializable
data class Picture(
    val title: String,
    val location: String,
    val imageUrl: String
)

// post 방식으로 요청 시 picture ID
@Serializable
data class PictureRequest(val pictureId:Int)
