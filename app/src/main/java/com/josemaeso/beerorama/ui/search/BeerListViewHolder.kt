package com.josemaeso.beerorama.ui.search

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.josemaeso.beerorama.R
import com.josemaeso.beerorama.domain.beer.Beer
import kotlinx.android.synthetic.main.search_beer_list_item.view.*

class BeerListViewHolder(itemView: View, private val listener: BeerListAdapter.BeerClickListener) : RecyclerView.ViewHolder(itemView) {
    fun bind(beer: Beer) {
        itemView.name.text = beer.name
        itemView.tagline.text = beer.tagline

        itemView.abv.text = itemView.context.getString(R.string.abv_label, beer.abv)

        itemView.setOnClickListener {
            listener.onBeerClick(beer)
        }
    }
}