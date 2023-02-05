package kz.tinkoff.kinopoisk.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import kotlinx.coroutines.launch

abstract class BaseViewModel: ViewModel(), AdapterListener {
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>(false)
    val directions = MutableLiveData<NavDirections>()

    fun <T> networkRequest(
        request: suspend () -> T,
        onSuccess: (T) -> Unit = {},
        onError: ((Exception) -> Unit)? = null,
        loadingState: MutableLiveData<Boolean>? = loading
    ) = viewModelScope.launch {
        if (loadingState?.value == null || loadingState.value == false) {
            loadingState?.value = true

            try {
                val response = request()
                loadingState?.value = false
                onSuccess(response)
            } catch (ex: Exception) {
                loadingState?.value = false
                onError?.invoke(ex)
            }
        }
    }

    fun navigate(navDirections: NavDirections) {
        directions.value = navDirections
    }


}