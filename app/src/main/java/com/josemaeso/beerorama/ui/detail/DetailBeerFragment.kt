package com.josemaeso.beerorama.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.josemaeso.beerorama.R
import com.josemaeso.beerorama.ui.loader.UIImageLoader
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.detail_beer_fragment.*
import javax.inject.Inject

@AndroidEntryPoint
class DetailBeerFragment : Fragment() {

    @Inject
    lateinit var imageLoader: UIImageLoader
    private val viewModel: DetailBeerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.detail_beer_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.beer.observe(viewLifecycleOwner) {
            it?.let { beer ->
                name.text = beer.name
                tagline.text = beer.tagline
                description.text = beer.description
                abv.text = getString(R.string.abv_label, beer.abv)

                beer.imageUrl?.let { imageUrl ->
                    imageLoader.loadImage(imageUrl, image, R.drawable.beer_place_holder)
                }
                progress_bar.isVisible = false
                beer_detail_container.isVisible = true
            }
        }
    }
}