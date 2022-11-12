package com.miso.vinilos.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.miso.vinilos.models.Artista
import com.miso.vinilos.network.NetworkServiceAdapter
import com.miso.vinilos.repositories.ArtistaDetailRepository


class ArtistaDetailViewModel (application: Application, id:String?) :  AndroidViewModel(application) {

    private val artistaDetailRepository = ArtistaDetailRepository(application)

    private val _artista = MutableLiveData<Artista>()

    val artista: LiveData<Artista>
        get() = _artista

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshDataFromNetwork(id)
    }

    private fun refreshDataFromNetwork(id:String?) {

        artistaDetailRepository.refreshData({
            _artista.postValue(it)
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        },{
            _eventNetworkError.value = true
        },id)

    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application,val id:String?) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ArtistaDetailViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ArtistaDetailViewModel(app,id) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
