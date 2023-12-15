package com.amnah.store.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductItemDto(
    @SerialName("category")
    val category: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("image")
    val image: String?,
    @SerialName("price")
    val price: Double?,
    @SerialName("rating")
    val ratingDto: RatingDto?,
    @SerialName("title")
    val title: String?
)