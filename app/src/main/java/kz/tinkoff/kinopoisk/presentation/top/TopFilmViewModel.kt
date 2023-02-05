package kz.tinkoff.kinopoisk.presentation.top

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kz.tinkoff.kinopoisk.data.mapper.FilmTopMapper
import kz.tinkoff.kinopoisk.data.models.TopFilmsDto
import kz.tinkoff.kinopoisk.domain.repo.FilmLocalRepository
import kz.tinkoff.kinopoisk.domain.repo.Baimurat
import kz.tinkoff.kinopoisk.presentation.adapter.FilmItem
import kz.tinkoff.kinopoisk.presentation.base.BaseViewModel
import kz.tinkoff.kinopoisk.presentation.base.ViewItem
import kz.tinkoff.kinopoisk.presentation.main.MainFragmentDirections

class TopFilmViewModel(
    private val repository: Baimurat,
    private val localRepository: FilmLocalRepository,
) : BaseViewModel() {
    private var filmsDto: List<TopFilmsDto.TopFilmDto> = mutableListOf()
    private val _filmItems = MutableLiveData<List<FilmItem>>()
    val filmItems: LiveData<List<FilmItem>> = _filmItems
    val favourites = localRepository.getFavourites()

    init {
        getFilms()
    }

    override fun onItemClick(item: ViewItem) {
        super.onItemClick(item)
        when (item) {
            is FilmItem -> {
                navigate(MainFragmentDirections.actionMainFragmentToFilmDetailFragment(item.id))
            }
        }
    }

    override fun onItemPressed(item: ViewItem) {
        super.onItemPressed(item)
        when (item) {
            is FilmItem -> setFavourite(item)
        }
    }


    fun getFilms() {
        networkRequest(
            request = { repository.getTopFilms() },
            onSuccess = {
                if (it.films != null) {
                    filmsDto = it.films
                }
                _filmItems.value = FilmTopMapper().fromTopFilmToItem(it).toMutableList()
                setupFavourites()
            },
            onError = {
                error.value = true
            },
        )
    }

    fun setupFavourites() {
        val filmItems = _filmItems.value.orEmpty().toMutableList()
        for (item in filmItems.indices) {
            filmItems[item].favourite = false
        }

        val filmWithId = filmItems.map { item -> item.id }
        val favouritesWithId = favourites.value?.map { it.filmId }
        favouritesWithId?.forEach { filmId ->

            if (filmWithId.contains(filmId)) {
                val index = filmItems.indexOfFirst { it.id == filmId }
                filmItems[index].favourite = true
            }
        }
        _filmItems.value = filmItems
    }


    private fun setFavourite(item: FilmItem) {
        if (favourites.value?.map { it.filmId }?.contains(item.id) == true) {
            deleteFromFavourite(item)
            return
        }
        val favouriteFilmDto = filmsDto.find { it.filmId == item.id }
        viewModelScope.launch {
            favouriteFilmDto?.let {
                localRepository.setFilmToFavourite(FilmTopMapper().fromItemToFavouriteFilm(it))
            }
        }
    }

    private fun deleteFromFavourite(item: FilmItem) {
        val favouriteFilmDto = filmsDto.find { it.filmId == item.id }
        viewModelScope.launch {
            favouriteFilmDto?.let {
                localRepository.deleteFilmFromFavourite(
                    FilmTopMapper().fromItemToFavouriteFilm(
                        it
                    )
                )
            }
        }
    }
}