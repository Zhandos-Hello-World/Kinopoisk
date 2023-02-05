package kz.tinkoff.kinopoisk.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import kz.tinkoff.kinopoisk.data.models.FavouriteFilmDto

const val DB_NAME = "KINOPOISK_DB"

@Database(entities = [FavouriteFilmDto::class], exportSchema = true, version = 1)
abstract class KinopoiskDatabase: RoomDatabase() {
    abstract fun filmsDao(): KinopoiskDao

}