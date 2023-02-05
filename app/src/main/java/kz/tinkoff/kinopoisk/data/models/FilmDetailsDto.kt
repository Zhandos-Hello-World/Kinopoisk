package kz.tinkoff.kinopoisk.data.models

data class FilmDetailsDto(
    val nameRu: String? = null,
    val posterUrl: String? = null,
    val description: String? = null,
    val genres: List<TopFilmsDto.TopFilmDto.Genre?>? = null,
    val countries: List<TopFilmsDto.TopFilmDto.Country?>? = null,
)