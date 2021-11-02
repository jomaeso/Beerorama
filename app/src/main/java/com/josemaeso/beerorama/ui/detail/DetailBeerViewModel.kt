package com.josemaeso.beerorama.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.josemaeso.beerorama.domain.beer.Beer
import com.josemaeso.beerorama.domain.beer.BeerProvider
import kotlinx.coroutines.Dispatchers

class DetailBeerViewModel(private val beerId: Int, private val beerProvider: BeerProvider): ViewModel() {
    val beer: LiveData<Beer?> by lazy {
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            val beer = beerProvider.getBeer(beerId)
            emit(beer)
        }
    }
}