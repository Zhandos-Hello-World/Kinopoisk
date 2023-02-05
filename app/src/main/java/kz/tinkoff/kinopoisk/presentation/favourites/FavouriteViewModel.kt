package kz.tinkoff.kinopoisk.presentation.favourites

import kz.tinkoff.kinopoisk.domain.repo.FilmLocalRepository
import kz.tinkoff.kinopoisk.presentation.adapter.FilmItem
import kz.tinkoff.kinopoisk.presentation.base.BaseViewModel
import kz.tinkoff.kinopoisk.presentation.base.ViewItem
import kz.tinkoff.kinopoisk.presentation.main.MainFragmentDirections

class FavouriteViewModel(localRepository: FilmLocalRepository) : BaseViewModel() {
    val favouriteFilms = localRepository.getFavourites()

    override fun onItemClick(item: ViewItem) {
        super.onItemClick(item)
        when (item) {
            is FilmItem -> {
                navigate(MainFragmentDirections.actionMainFragmentToFilmDetailFragment(item.id))
            }
        }
    }

}