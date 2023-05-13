package com.example.ktorexample.ktor

import android.content.ContentValues
import android.util.Log
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.get
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import java.nio.channels.UnresolvedAddressException

// http client 생성 및 server request 구현
class RestFulRepository {
    companion object {
        private const val TAG = "RestFulRepository"
        const val BASE_URL = "http://192.168.0.6"
    }

    private val client: HttpClient

    // json 설정
    private val json = kotlinx.serialization.json.Json {
        encodeDefaults = true   // Null인 값도 json 에 포함시킨다
        ignoreUnknownKeys = true    // 모델에 없고, json 에 있는 경우 해당 키 무시
    }

    init {
        client = HttpClient {
            // Header 설
            install(DefaultRequest) {
                header("Accept", "application/json")
                header("Content-type", "application/json")
            }

            // json 설정
            install(JsonFeature) {
                serializer = KotlinxSerializer(json = json)
            }

            // Logging 설정
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.d(ContentValues.TAG, "api log: $message")
                    }
                }
                level = LogLevel.ALL
            }
            // Timeout 설정
            install(HttpTimeout) {
                requestTimeoutMillis = 10000
                connectTimeoutMillis = 10000
                socketTimeoutMillis = 10000
            }
            // 기본적인 api 호출시 넣는 것들 = 기본 세팅
            defaultRequest {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
            }

        }
    }

    // get post는 모두 suspend function이므로 coroutine scope에서 호출되어야 함
    // Main thread에서 호출 금지

    // Get 사용
    @Throws
    suspend fun getPictureByGet(id: Int) =
        client.get<Picture>(BASE_URL + "/picture") {
            parameter("id", 0)
        }

    // Post 사용
    @Throws
    suspend fun getPictureByPost(pictureRequest: PictureRequest) =
        client.post<Picture>(BASE_URL + "/picture") {
            body = pictureRequest
        }
    // exception 처리
    fun getErrorStatus(th: Throwable): Int = when (th) {
        is RedirectResponseException -> { //Http Code: 3xx
            (th.response.status.value)
        }
        is ClientRequestException -> { //Http Code: 4xx
            (th.response.status.value)
        }
        is ServerResponseException -> { //Http Code: 5xx
            (th.response.status.value)
        }
        is UnresolvedAddressException -> { // Network Error - Internet Error
            1000
        }
        else -> 9999 // Unknown
    }

}