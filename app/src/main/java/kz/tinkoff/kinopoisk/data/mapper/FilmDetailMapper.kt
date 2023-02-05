package kz.tinkoff.kinopoisk.data.mapper

import android.util.Log
import kz.tinkoff.kinopoisk.data.models.FilmDetailsDto
import kz.tinkoff.kinopoisk.domain.model.FilmDetails

class FilmDetailMapper {

    fun fromDetailToModel(dto: FilmDetailsDto): FilmDetails {
        return FilmDetails(
            dto.posterUrl ?: "",
            dto.nameRu ?: "",
            dto.description ?: "",
            dto.genres?.map { it?.genre }.toString(),
            dto.countries?.map { it?.country }.toString(),
        )
    }
}