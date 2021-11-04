package com.josemaeso.beerorama.domain.beer

import com.josemaeso.beerorama.data.beer.api.RemoteApiBeer

class BeerMapper {
    fun remoteToDomain(remoteApiBeer: RemoteApiBeer): Beer {
        return Beer(
            remoteApiBeer.id,
            remoteApiBeer.name,
            remoteApiBeer.tagline,
            remoteApiBeer.abv,
            remoteApiBeer.description,
            remoteApiBeer.image_url
        )
    }

    fun remoteToDomainList(remoteApiBeers: List<RemoteApiBeer>): List<Beer> {
        return remoteApiBeers.map {
            remoteToDomain(it)
        }
    }
}