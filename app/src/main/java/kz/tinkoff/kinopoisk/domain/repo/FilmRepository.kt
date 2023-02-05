package kz.tinkoff.kinopoisk.domain.repo

import kz.tinkoff.kinopoisk.data.models.FilmDetailsDto
import kz.tinkoff.kinopoisk.data.models.TopFilmsDto

interface FilmRepository {

    suspend fun getTopFilms(): TopFilmsDto


    suspend fun getFilmById(id: Int): FilmDetailsDto


}