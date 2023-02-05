package kz.tinkoff.kinopoisk.data.mapper

import kz.tinkoff.kinopoisk.data.models.FavouriteFilmDto
import kz.tinkoff.kinopoisk.data.models.TopFilmsDto
import kz.tinkoff.kinopoisk.presentation.adapter.FilmItem

class FilmTopMapper {

    fun fromTopFilmToItem(topFilmsDto: TopFilmsDto): List<FilmItem> {
        val films = topFilmsDto.films ?: return listOf()
        val items = mutableListOf<FilmItem>()
        films.forEach {
            if (it.filmId != null && it.nameEn != null && it.year != null && it.posterUrlPreview != null) {
                items.add(FilmItem(it.filmId, it.posterUrlPreview, it.nameEn, it.year))
            }
        }
        return items
    }

    fun fromItemToFavouriteFilm(filmItem: TopFilmsDto.TopFilmDto): FavouriteFilmDto {
        return FavouriteFilmDto(
            filmId = filmItem.filmId,
            nameRu = filmItem.nameRu,
            nameEn = filmItem.nameEn,
            year = filmItem.year,
            genres = filmItem.genres?.get(0)?.genre.orEmpty(),
            posterUrlPreview = filmItem.posterUrlPreview
        )
    }
}