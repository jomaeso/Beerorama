package com.josemaeso.beerorama.di

import com.josemaeso.beerorama.data.beer.api.BeerMapper
import com.josemaeso.beerorama.data.beer.api.HttpBeerProvider
import com.josemaeso.beerorama.data.beer.api.PunkApiService
import com.josemaeso.beerorama.domain.beer.BeerProvider
import com.josemaeso.beerorama.ui.loader.PicassoImageLoader
import com.josemaeso.beerorama.ui.loader.UIImageLoader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun beerProvider(): BeerProvider {
        return HttpBeerProvider(apiServiceProvider(), BeerMapper())
    }

    @Provides
    @Singleton
    fun imageLoaderProvider(): UIImageLoader {
        return PicassoImageLoader()
    }

    private fun apiServiceProvider(): PunkApiService {
        val retrofit = Retrofit.Builder().baseUrl("https://api.punkapi.com").addConverterFactory(
            MoshiConverterFactory.create()
        )
            .build()
        return retrofit.create(PunkApiService::class.java)
    }
}