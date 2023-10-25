package com.myapp.ccounter.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.core.use
import kotlinx.serialization.json.Json

internal class ProductService: KtorApi() {
    suspend fun getProductsList(productsName: String): ProductsListResponse {
        return HttpClient {
            expectSuccess = true
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                })
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        co.touchlab.kermit.Logger.d(
                            tag = "HTTP_CLIENT",
                            messageString = message
                        )
                    }
                }
                level = LogLevel.ALL
            }
        }.use { client ->
            client.get {
                pathUrl("food/products/search")
                parameter("query", productsName)
                parameter("number", 10)
            }.body()
        }
    }

    suspend fun getProduct(productId: Int): ProductRemote {
        return HttpClient {
            expectSuccess = true
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                })
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        co.touchlab.kermit.Logger.d(
                            tag = "HTTP_CLIENT",
                            messageString = message
                        )
                    }
                }
                level = LogLevel.ALL
            }
        }.use { client ->
            client.get {
                pathUrl("food/products/${productId}")
            }.body()
        }
    }
}
