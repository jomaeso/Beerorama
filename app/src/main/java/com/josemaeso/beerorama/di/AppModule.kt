package com.josemaeso.beerorama.di

import com.josemaeso.beerorama.data.beer.api.BeerMapper
import com.josemaeso.beerorama.data.beer.api.HttpBeerProvider
import com.josemaeso.beerorama.data.beer.api.PunkApiService
import com.josemaeso.beerorama.domain.beer.BeerProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun beerProvider(): BeerProvider {
        return HttpBeerProvider(apiServiceProvider(), BeerMapper())
    }

    private fun apiServiceProvider(): PunkApiService {
        val retrofit = Retrofit.Builder().baseUrl("https://api.punkapi.com").addConverterFactory(
            GsonConverterFactory.create()
        )
            .build()
        return retrofit.create(PunkApiService::class.java)
    }
}