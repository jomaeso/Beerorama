package com.josemaeso.beerorama

import android.app.Application
import com.josemaeso.beerorama.data.beer.api.HttpBeerProvider
import com.josemaeso.beerorama.data.beer.api.PunkApiService
import com.josemaeso.beerorama.domain.beer.BeerMapper
import com.josemaeso.beerorama.domain.beer.BeerProvider
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BeeroramaApplication : Application() {
    lateinit var beerProvider: BeerProvider

    override fun onCreate() {
        super.onCreate()

        beerProvider = HttpBeerProvider(retrofitLoader(), BeerMapper())
    }

    private fun retrofitLoader(): PunkApiService {
        val retrofit = Retrofit.Builder().baseUrl("https://api.punkapi.com").addConverterFactory(
            GsonConverterFactory.create()
        )
            .build()
        return retrofit.create(PunkApiService::class.java)
    }
}