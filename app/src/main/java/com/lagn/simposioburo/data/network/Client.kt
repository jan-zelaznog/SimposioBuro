package com.lagn.simposioburo.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Client {
    public var BASE_URL: String = "https://pruebas-simposioburo.burodecredito.com.mx"
    public var retrofit: Retrofit? = null

    public fun getApiClient(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
        return retrofit
    }
}