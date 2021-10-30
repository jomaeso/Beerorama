package com.josemaeso.beerorama.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.josemaeso.beerorama.BeeroramaApplication
import com.josemaeso.beerorama.R
import kotlinx.android.synthetic.main.search_beer_fragment.*

class SearchBeerFragment : Fragment() {

    private val viewModel: SearchBeerViewModel by viewModels{SearchBeerViewModelFactory((activity?.application as BeeroramaApplication).beerProvider)}
    private val beerAdapter = BeerListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.search_beer_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_beer_list.adapter = beerAdapter
        rv_beer_list.layoutManager = LinearLayoutManager(requireContext())

        sv_search_beer.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.filterBeer(newText)
                return false
            }
        })

        viewModel.beersLiveData.observe(viewLifecycleOwner) {
            beerAdapter.submitList(it)
        }
    }

}