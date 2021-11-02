package com.josemaeso.beerorama

import com.josemaeso.beerorama.data.beer.api.HttpBeerProvider
import com.josemaeso.beerorama.data.beer.api.PunkApiService
import com.josemaeso.beerorama.data.beer.api.RemoteApiBeer
import com.josemaeso.beerorama.domain.beer.BeerMapper
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class HttpBeerProviderTest {
    @Mock
    private lateinit var punkApiService: PunkApiService

    @Test
    fun test_getBeers_successNotEmpty() = runBlocking {
        val remoteBeers = listOf(
            BeerProviderTestUtils.createRemoteBeer(),
            BeerProviderTestUtils.createRemoteBeer()
        )
        val sut = makeSUT(punkApiService)

        whenever(punkApiService.getBeers()).thenReturn(Response.success(remoteBeers))

        val beers = sut.getBeers()

        verify(punkApiService, times(1)).getBeers()
        assertEquals(remoteBeers.map { it.id }, beers.map { it.id })
    }

    @Test
    fun test_getBeers_successNotEmptyWithFilter() = runBlocking {
        val remoteBeers = listOf(
            BeerProviderTestUtils.createRemoteBeer(),
            BeerProviderTestUtils.createRemoteBeer()
        )
        val filter = "beer"
        val sut = makeSUT(punkApiService)

        whenever(punkApiService.filterBeers(filter)).thenReturn(Response.success(remoteBeers))

        val beers = sut.getBeers(filter = filter)

        verify(punkApiService, times(1)).filterBeers(filter)
        assertEquals(remoteBeers.map { it.id }, beers.map { it.id })
    }

    @Test
    fun test_getBeers_successEmpty() = runBlocking {
        val sut = makeSUT(punkApiService)

        whenever(punkApiService.getBeers()).thenReturn(Response.success(emptyList()))

        val beers = sut.getBeers()

        verify(punkApiService, times(1)).getBeers()
        assertTrue(beers.isEmpty())
    }

    @Test
    fun test_getBeers_error() = runBlocking {
        val sut = makeSUT(punkApiService)

        whenever(punkApiService.getBeers()).thenReturn(
            Response.error(
                404,
                ResponseBody.create(null, "")
            )
        )

        val beers = sut.getBeers()

        verify(punkApiService, times(1)).getBeers()
        assertTrue(beers.isEmpty())
    }

    @Test
    fun test_getBeer_success() = runBlocking {
        val sut = makeSUT(punkApiService)
        val beerId = 123456
        val beerDetail = listOf(BeerProviderTestUtils.createRemoteBeer(id = beerId))
        whenever(punkApiService.getBeer(beerId)).thenReturn( Response.success(beerDetail))

        val beer = sut.getBeer(beerId)

        assertEquals(beerId, beer?.id)
    }

    @Test
    fun test_getBeer_empty() = runBlocking {
        val sut = makeSUT(punkApiService)
        val beerId = 123456
        val beerDetail = emptyList<RemoteApiBeer>()
        whenever(punkApiService.getBeer(beerId)).thenReturn( Response.success(beerDetail))

        val beer = sut.getBeer(beerId)

        assertNull(beer)
    }

    @Test
    fun test_getBeer_error() = runBlocking {
        val sut = makeSUT(punkApiService)
        val beerId = 123456
        whenever(punkApiService.getBeer(beerId)).thenReturn( Response.error(404, ResponseBody.create(null, "")))

        val beer = sut.getBeer(beerId)

        assertNull(beer)
    }

    private fun makeSUT(punkApiService: PunkApiService): HttpBeerProvider {
        return HttpBeerProvider(punkApiService, BeerMapper())
    }
}