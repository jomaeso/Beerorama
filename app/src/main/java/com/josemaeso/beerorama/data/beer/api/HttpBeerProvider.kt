package com.josemaeso.beerorama.data.beer.api

import com.josemaeso.beerorama.domain.beer.Beer
import com.josemaeso.beerorama.domain.beer.BeerProvider

class HttpBeerProvider(private val apiService: PunkApiService, private val mapper: BeerMapper) :
    BeerProvider {
    override suspend fun getBeers(filter: String?): List<Beer> {
        val response = if (filter != null && filter.isNotEmpty()) {
            apiService.filterBeers(filter)
        } else {
            apiService.getBeers()
        }

        if (response.isSuccessful) {
            response.body()?.let {
                return mapper.remoteToDomainList(it)
            }
        }

        return emptyList()
    }

    override suspend fun getBeer(id: Int): Beer? {
        val response = apiService.getBeer(id)
        if(response.isSuccessful) {
            response.body()?.let {
                if(it.isNotEmpty()) {
                    return mapper.remoteToDomain(it.first())
                }
            }
        }
        return null
    }
}