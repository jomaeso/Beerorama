package com.josemaeso.beerorama.domain.beer

interface BeerProvider {
    suspend fun getBeers(filter: String? = null): List<Beer>
}