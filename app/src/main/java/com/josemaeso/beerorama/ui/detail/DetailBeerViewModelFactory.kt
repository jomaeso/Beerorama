package com.josemaeso.beerorama.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.josemaeso.beerorama.domain.beer.BeerProvider

class DetailBeerViewModelFactory (private val beerId: Int, private val beerProvider: BeerProvider) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Int::class.java, BeerProvider::class.java).newInstance(beerId, beerProvider)
    }
}