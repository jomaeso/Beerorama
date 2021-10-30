package com.josemaeso.beerorama.data.beer.api

import com.josemaeso.beerorama.domain.beer.Beer
import com.josemaeso.beerorama.domain.beer.BeerMapper
import com.josemaeso.beerorama.domain.beer.BeerProvider

class HttpBeerProvider(private val apiService: PunkApiService, private val mapper: BeerMapper) :
    BeerProvider {
    override suspend fun getBeers(filter: String?): List<Beer> {
        val response = if (filter != null && filter.isNotEmpty()) {
            apiService.filterRockets(filter)
        } else {
            apiService.getRockets()
        }

        if (response.isSuccessful) {
            response.body()?.let {
                return mapper.remoteToDomainList(it)
            }
        }

        return emptyList()
    }
}