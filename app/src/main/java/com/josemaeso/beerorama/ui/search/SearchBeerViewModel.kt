package com.josemaeso.beerorama.ui.search

import androidx.lifecycle.*
import com.josemaeso.beerorama.domain.beer.Beer
import com.josemaeso.beerorama.domain.beer.BeerProvider
import com.josemaeso.beerorama.ui.Event
import kotlinx.coroutines.Dispatchers.IO

class SearchBeerViewModel(private val beerProvider: BeerProvider) : ViewModel() {
    private val beerFilterEvent = MutableLiveData<String?>(null)

    val navigateDetailEvent = MutableLiveData<Event<Beer>>()
    val beersLiveData: LiveData<List<Beer>> by lazy {
        beerFilterEvent.switchMap { beerFilter ->
            liveData(context = viewModelScope.coroutineContext + IO) {
                emit(beerProvider.getBeers(beerFilter))
            }
        }
    }

    fun filterBeer(filter: String?) {
        beerFilterEvent.postValue(filter)
    }

    fun selectBeer(beer: Beer) {
        navigateDetailEvent.postValue(Event(beer))
    }
}