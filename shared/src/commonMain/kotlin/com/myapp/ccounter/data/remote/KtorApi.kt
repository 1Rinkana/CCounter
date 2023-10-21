package com.myapp.ccounter.data.remote

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.http.path
import io.ktor.http.takeFrom

private const val BASE_URL = "https://api.spoonacular.com/"
private const val API_KEY = "c940f9ef2c7747369f102cf8fa648711"

abstract class KtorApi {
//    val client = HttpClient {
//        expectSuccess = true
//        install(ContentNegotiation) {
//            json(Json {
//                ignoreUnknownKeys = true
//                prettyPrint = true
//                isLenient = true
//            })
//        }
//        install(Logging) {
//            logger = object : Logger {
//                override fun log(message: String) {
//                    co.touchlab.kermit.Logger.d(tag = "HTTP_CLIENT",
//                        messageString = message)
//                }
//            }
//            level = LogLevel.ALL
//        }
//    }

    fun HttpRequestBuilder.pathUrl(path: String) {
        url {
            takeFrom(BASE_URL)
            path(path)
            parameter("apiKey", API_KEY)
        }
    }
}
