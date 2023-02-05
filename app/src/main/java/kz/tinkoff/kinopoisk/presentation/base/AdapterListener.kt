package kz.tinkoff.kinopoisk.presentation.base


interface AdapterListener {

    fun onItemClick(item: ViewItem) { }

    fun onItemPressed(item: ViewItem) { }
}