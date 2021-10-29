package com.josemaeso.beerorama.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.josemaeso.beerorama.BeeroramaApplication
import com.josemaeso.beerorama.R

class SearchBeerFragment : Fragment() {

    private val viewModel: SearchBeerViewModel by viewModels{SearchBeerViewModelFactory((activity?.application as BeeroramaApplication).beerProvider)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.search_beer_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.beersLiveData.observe(viewLifecycleOwner) {

        }
    }

}