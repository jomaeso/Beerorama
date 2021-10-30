package com.josemaeso.beerorama.domain.beer

data class Beer(
    val id: Int,
    val name: String,
    val tagline: String,
    val abv: Float,
    val description: String,
    val imageUrl: String?
)
