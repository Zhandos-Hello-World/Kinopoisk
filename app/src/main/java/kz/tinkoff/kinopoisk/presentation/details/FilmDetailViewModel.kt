package kz.tinkoff.kinopoisk.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kz.tinkoff.kinopoisk.data.mapper.FilmDetailMapper
import kz.tinkoff.kinopoisk.domain.model.FilmDetails
import kz.tinkoff.kinopoisk.domain.repo.FilmRepository
import kz.tinkoff.kinopoisk.presentation.base.BaseViewModel

class FilmDetailViewModel(private val repository: FilmRepository) : BaseViewModel() {
    private val _details = MutableLiveData<FilmDetails>()
    val details: LiveData<FilmDetails> = _details

    fun getById(id: Int) {
        networkRequest(
            request = { repository.getFilmById(id) },
            onSuccess = { _details.value = FilmDetailMapper().fromDetailToModel(it) },
            onError = { error.value = true }
        )
    }

}