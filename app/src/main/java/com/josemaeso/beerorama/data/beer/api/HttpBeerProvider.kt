package com.josemaeso.beerorama.data.beer.api

import com.josemaeso.beerorama.domain.beer.Beer
import com.josemaeso.beerorama.domain.beer.BeerProvider

class HttpBeerProvider: BeerProvider {
    override suspend fun getBeers(filter: String?): List<Beer> {
        return listOf()
    }
}