package com.amnah.store.di

import com.amnah.store.data.remote.IProductsRemoteApi
import com.amnah.store.data.remote.ProductsRemoteApi
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val GatewayModule = module {

    singleOf(::ProductsRemoteApi) { bind<IProductsRemoteApi>() }
}