package kz.tinkoff.kinopoisk.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_films_table")
data class FavouriteFilmDto(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo("film_id")
    val filmId: Int? = null,
    @ColumnInfo("name_ru")
    val nameRu: String? = null,
    @ColumnInfo("name_en")
    val nameEn: String? = null,
    val year: String? = null,
    val genres: String? = null,
    @ColumnInfo("poster_url_preview")
    val posterUrlPreview: String? = null,
)