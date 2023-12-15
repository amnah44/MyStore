package com.amnah.store.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import com.amnah.store.ui.presenter.store.StoreViewModel

val viewModelModule = module {
    viewModelOf(::StoreViewModel)
}