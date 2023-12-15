package com.amnah.store.di

import org.koin.dsl.module

fun appModule() = module {
    includes(
        viewModelModule,
        GatewayModule,
        NetworkModule,
    )
}