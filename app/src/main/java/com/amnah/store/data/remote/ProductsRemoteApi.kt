package com.amnah.store.data.remote

import com.amnah.store.data.model.ProductItemDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get

interface IProductsRemoteApi {

    suspend fun getProducts(): List<ProductItemDto>
}

class ProductsRemoteApi(
    client: HttpClient,
) : IProductsRemoteApi, BaseRemoteApi(client) {

    override suspend fun getProducts(): List<ProductItemDto> {
        val result = tryToExecute<List<ProductItemDto>> {
            get("/products/")
        }
        return result
    }


}