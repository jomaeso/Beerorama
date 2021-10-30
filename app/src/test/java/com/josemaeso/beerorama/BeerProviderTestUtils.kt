package com.josemaeso.beerorama

import com.josemaeso.beerorama.data.beer.api.RemoteApiBeer

object BeerProviderTestUtils {
    fun createRemoteBeer(
        id: Int = (1000..9999).random(),
        name: String = "Beer name",
        tagline: String = "The best beer",
        abv: Float = 3.7f,
        description: String = "The description of the beer",
        imageUrl: String = "https://test.test/image.jpg"
    ): RemoteApiBeer {
        return RemoteApiBeer(
            id,
            name,
            tagline,
            abv,
            description,
            imageUrl
        )
    }
}