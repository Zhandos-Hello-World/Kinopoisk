package kz.tinkoff.kinopoisk.data.repo

import kz.tinkoff.kinopoisk.data.api.FilmApi
import kz.tinkoff.kinopoisk.data.models.FilmDetailsDto
import kz.tinkoff.kinopoisk.data.models.TopFilmsDto
import kz.tinkoff.kinopoisk.domain.repo.FilmRepository
import kz.tinkoff.kinopoisk.domain.repo.NetworkRepository

class FilmRepositoryImpl(private val api: FilmApi) : FilmRepository, NetworkRepository() {

    override suspend fun getTopFilms(): TopFilmsDto {
        return request { api.getTopFilms() }
    }

    override suspend fun getFilmById(id: Int): FilmDetailsDto {
        return request { api.getFilmById(id) }
    }

}