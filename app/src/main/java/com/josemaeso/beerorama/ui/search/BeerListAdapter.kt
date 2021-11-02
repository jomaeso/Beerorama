package com.josemaeso.beerorama.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.josemaeso.beerorama.R
import com.josemaeso.beerorama.domain.beer.Beer

class BeerListAdapter(private val listener: BeerClickListener): ListAdapter<Beer, BeerListViewHolder>(DiffCallback()) {
    private class DiffCallback : DiffUtil.ItemCallback<Beer>() {
        override fun areItemsTheSame(oldItem: Beer, newItem: Beer) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Beer, newItem: Beer) =
            true
    }

    interface BeerClickListener {
        fun onBeerClick(beer: Beer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerListViewHolder {
        val view = LayoutInflater.from(parent.context).
        inflate(R.layout.search_beer_list_item, parent, false) as View
        return BeerListViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: BeerListViewHolder, position: Int) {
        val beer = currentList[position]
        holder.bind(beer)
    }
}