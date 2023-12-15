package com.amnah.store.ui.presenter.store

import com.amnah.store.data.model.ProductItemDto
import com.amnah.store.data.remote.IProductsRemoteApi
import com.amnah.store.ui.presenter.base.BaseViewModel

class StoreViewModel(
    private val productsRemoteApi: IProductsRemoteApi,
) : BaseViewModel<StoreUIState, StoreUIEffect>(StoreUIState()) {


    init {
        getAllProducts()
    }

    fun getAllProducts(){
        updateState { it.copy(isLoading = true) }
        getStoreProducts()
    }

    private fun getStoreProducts() {
        tryToExecute(
            { productsRemoteApi.getProducts() },
            ::onSuccess,
            ::onError
        )
    }

    private fun onSuccess(products: List<ProductItemDto>) {
        updateState {
            StoreUIState(
                isSuccess = true,
                isError = false,
                isLoading = false,
                products = products
            )
        }
    }

    private fun onError() {
        updateState {
            it.copy(
                isError = true,
                isLoading = false,
                isSuccess = false
            )
        }
        sendNewEffect(StoreUIEffect.StoreError)
    }


}