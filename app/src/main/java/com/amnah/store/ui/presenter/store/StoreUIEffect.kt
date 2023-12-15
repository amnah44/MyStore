package com.amnah.store.ui.presenter.store

sealed interface StoreUIEffect {

    object StoreError : StoreUIEffect

}