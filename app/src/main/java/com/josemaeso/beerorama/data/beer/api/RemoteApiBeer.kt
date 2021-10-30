package com.josemaeso.beerorama.data.beer.api

data class RemoteApiBeer(
    val id: Int,
    val name: String,
    val tagline: String,
    val abv: Float,
    val description: String,
    val image_url: String?
)
