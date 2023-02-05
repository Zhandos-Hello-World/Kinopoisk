package kz.tinkoff.kinopoisk.presentation.adapter

import kz.tinkoff.kinopoisk.presentation.base.ViewItem

data class FilmItem(
    val id: Int,
    val imageUrl: String,
    val name: String,
    val time: String,
    var favourite: Boolean = false
) : ViewItem