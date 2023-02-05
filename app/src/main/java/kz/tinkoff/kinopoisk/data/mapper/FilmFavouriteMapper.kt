package kz.tinkoff.kinopoisk.data.mapper

import kz.tinkoff.kinopoisk.data.models.FavouriteFilmDto
import kz.tinkoff.kinopoisk.presentation.adapter.FilmItem

class FilmFavouriteMapper {

    private fun fromFavouriteToItem(dto: FavouriteFilmDto): FilmItem {
        return FilmItem(
            dto.filmId ?: -1,
            dto.posterUrlPreview ?: "",
            dto.nameRu ?: "",
            dto.year ?: "",
            true
        )
    }

    fun fromFavouritesToItems(dtos: List<FavouriteFilmDto>): List<FilmItem> {
        val items = mutableListOf<FilmItem>()
        dtos.forEach {
            items.add(fromFavouriteToItem(it))
        }
        return items
    }
}