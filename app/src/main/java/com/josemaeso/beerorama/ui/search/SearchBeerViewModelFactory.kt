package com.josemaeso.beerorama.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.josemaeso.beerorama.domain.beer.BeerProvider

class SearchBeerViewModelFactory (private val beerProvider: BeerProvider): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(BeerProvider::class.java).newInstance(beerProvider)
    }
}