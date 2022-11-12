package com.miso.vinilos.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.miso.vinilos.models.Coleccionista
import com.miso.vinilos.repositories.ColeccionistaRepository

class ColeccionistaViewModel(application: Application) :  AndroidViewModel(application) {

    private val coleccionistaRepository = ColeccionistaRepository(application)

    private val _coleccionista= MutableLiveData<List<Coleccionista>>()

    val coleccionista: LiveData<List<Coleccionista>>
        get() = _coleccionista

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        coleccionistaRepository.refreshData({
            _coleccionista.postValue(it)
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        },{
            _eventNetworkError.value = true
        })
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ColeccionistaViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ColeccionistaViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
