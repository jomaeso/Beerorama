package com.josemaeso.beerorama.ui.loader

import android.widget.ImageView
import com.squareup.picasso.Picasso

class PicassoImageLoader : UIImageLoader {
    override fun loadImage(imageUrl: String, view: ImageView, placeholderResource: Int) {
        Picasso.get().load(imageUrl)
            .placeholder(placeholderResource).fit().centerCrop()
            .into(view)
    }

}