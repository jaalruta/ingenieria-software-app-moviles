package com.miso.vinilos.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.miso.vinilos.models.Album
import com.miso.vinilos.network.NetworkServiceAdapter
class AlbumDetailViewModel (application: Application, id:String?) :  AndroidViewModel(application) {

    private val _albums = MutableLiveData<Album>()

    val albums: LiveData<Album>
        get() = _albums

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
        NetworkServiceAdapter.getInstance(getApplication()).getAlbum({
            _albums.postValue(it)
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
            if (modelClass.isAssignableFrom(AlbumDetailViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AlbumDetailViewModel(app,id) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
