package com.amnah.store.ui.presenter.store

import com.amnah.store.data.model.ProductItemDto

data class StoreUIState(
    val isSuccess: Boolean = false,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val products: List<ProductItemDto> = emptyList(),
)