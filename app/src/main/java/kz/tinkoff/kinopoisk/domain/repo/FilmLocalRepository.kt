package kz.tinkoff.kinopoisk.domain.repo

import androidx.lifecycle.LiveData
import kz.tinkoff.kinopoisk.data.models.FavouriteFilmDto

interface FilmLocalRepository {

    fun getFavourites(): LiveData<List<FavouriteFilmDto>>

    fun getFavouritesOnlyId(): LiveData<List<Int?>>

    suspend fun setFilmToFavourite(item: FavouriteFilmDto)

    suspend fun deleteFilmFromFavourite(item: FavouriteFilmDto)

}
