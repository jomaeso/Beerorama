package com.josemaeso.beerorama.data.beer.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PunkApiService {
    @GET("/v2/beers")
    suspend fun getRockets(): Response<List<RemoteApiBeer>>

    @GET("/v2/beers")
    suspend fun filterRockets(@Query("beer_name") name: String): Response<List<RemoteApiBeer>>
}