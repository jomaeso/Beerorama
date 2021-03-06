package com.josemaeso.beerorama.data.beer.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PunkApiService {
    @GET("/v2/beers")
    suspend fun getBeers(): Response<List<RemoteApiBeer>>

    @GET("/v2/beers")
    suspend fun filterBeers(@Query("beer_name") name: String): Response<List<RemoteApiBeer>>

    @GET("/v2/beers/{id}")
    suspend fun getBeer(@Path("id") id: Int): Response<List<RemoteApiBeer>>
}