package kz.tinkoff.kinopoisk.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kz.tinkoff.kinopoisk.data.db.KinopoiskDao
import kz.tinkoff.kinopoisk.data.models.FavouriteFilmDto
import kz.tinkoff.kinopoisk.domain.repo.FilmLocalRepository

class FilmLocalRepositoryImpl(private val dao: KinopoiskDao): FilmLocalRepository {

    override fun getFavourites(): LiveData<List<FavouriteFilmDto>> {
        return dao.getAllFavouriteFilms()
    }


    override fun getFavouritesOnlyId(): LiveData<List<Int?>> {
        return Transformations.map(dao.getAllFavouriteFilms()) { favourites ->
            favourites.map { it.filmId }
        }
    }

    override suspend fun setFilmToFavourite(item: FavouriteFilmDto) {
        dao.setFilmToFavourite(item)
    }

    override suspend fun deleteFilmFromFavourite(item: FavouriteFilmDto) {
        item.filmId?.let {
            dao.deleteFilmFromFavourite(it)
        }
    }

}