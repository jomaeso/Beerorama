package com.josemaeso.beerorama.ui.detail

import androidx.lifecycle.*
import com.josemaeso.beerorama.domain.beer.Beer
import com.josemaeso.beerorama.domain.beer.BeerProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class DetailBeerViewModel @Inject constructor(
    beerProvider: BeerProvider,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val beer: LiveData<Beer?> by lazy {
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            val beerId: Int = savedStateHandle["beerId"]!!
            val beer = beerProvider.getBeer(beerId)
            emit(beer)
        }
    }
}