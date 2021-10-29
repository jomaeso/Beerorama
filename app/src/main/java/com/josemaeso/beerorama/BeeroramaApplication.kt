package com.josemaeso.beerorama

import android.app.Application
import com.josemaeso.beerorama.data.beer.api.HttpBeerProvider
import com.josemaeso.beerorama.domain.beer.BeerProvider

class BeeroramaApplication: Application() {
    lateinit var beerProvider: BeerProvider

    override fun onCreate() {
        super.onCreate()

        beerProvider = HttpBeerProvider()
    }
}