package kz.tinkoff.kinopoisk.data.api

import kz.tinkoff.kinopoisk.data.models.FilmDetailsDto
import kz.tinkoff.kinopoisk.data.models.TopFilmsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FilmApi {

    @GET("/api/v2.2/films/top")
    suspend fun getTopFilms(): Response<TopFilmsDto>


    @GET("/api/v2.2/films/{id}")
    suspend fun getFilmById(@Path("id") id: Int): Response<FilmDetailsDto>


}