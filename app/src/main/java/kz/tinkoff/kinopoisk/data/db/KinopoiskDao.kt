package kz.tinkoff.kinopoisk.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kz.tinkoff.kinopoisk.data.models.FavouriteFilmDto

@Dao
interface KinopoiskDao {

    @Query("SELECT * FROM favourite_films_table")
    fun getAllFavouriteFilms(): LiveData<List<FavouriteFilmDto>>

    @Insert(entity = FavouriteFilmDto::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun setFilmToFavourite(item: FavouriteFilmDto)


    @Query("DELETE FROM favourite_films_table WHERE film_id = :filmId")
    suspend fun deleteFilmFromFavourite(filmId: Int)

}