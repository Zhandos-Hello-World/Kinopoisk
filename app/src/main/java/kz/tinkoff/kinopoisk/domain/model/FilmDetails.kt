package kz.tinkoff.kinopoisk.domain.model

data class FilmDetails(
    val imageUrl: String,
    val title: String,
    val description: String,
    val genres: String,
    val countries: String
)