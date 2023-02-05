package kz.tinkoff.kinopoisk.data.models

data class TopFilmsDto(
    val pagesCount: Int? = null,
    val films: List<TopFilmDto>? = null,
) {
    data class TopFilmDto(
        val filmId: Int? = null,
        val nameRu: String? = null,
        val nameEn: String? = null,
        val year: String? = null,
        val filmLength: String? = null,
        val countries: List<Country>? = null,
        val genres: List<Genre>? = null,
        val rating: String? = null,
        val ratingVoteCount: String? = null,
        val posterUrl: String? = null,
        val posterUrlPreview: String? = null,
        val ratingChange: String? = null,
    ) {

        data class Country(
            val country: String? = null,
        )

        data class Genre(
            val genre: String? = null,
        )
    }
}