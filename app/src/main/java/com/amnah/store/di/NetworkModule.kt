package com.amnah.store.di

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module


val NetworkModule = module {
    single {
        val client = HttpClient(CIO) {
            expectSuccess = true


            install(Logging) {
                logger = CustomHttpLogger()
                level = LogLevel.BODY
            }

            defaultRequest {
                url("https://fakestoreapi.com")
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }
        }

        client
    }
}


class CustomHttpLogger() : Logger {
    override fun log(message: String) {
        Log.d("loggerTag", message)
    }
}