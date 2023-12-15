package com.amnah.store.data.remote

import com.amnah.store.utils.AuthenticationFailure
import com.amnah.store.utils.NoInternetConnection
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.statement.HttpResponse

abstract class BaseRemoteApi(val client: HttpClient) {

    protected suspend inline fun <reified T> tryToExecute(
        method: HttpClient.() -> HttpResponse
    ): T {
        try {
            return client.method().body()
        } catch (e: ClientRequestException) {
            throw AuthenticationFailure()
        } catch (e: NoInternetConnection) {
            throw NoInternetConnection()
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }
}