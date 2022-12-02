package com.miso.vinilos.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.android.volley.VolleyError
import com.google.gson.Gson
import com.miso.vinilos.models.Album
import com.miso.vinilos.repositories.AlbumDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import com.miso.vinilos.models.Comentario
import com.miso.vinilos.models.CollectorComentario


class AlbumDetailViewModel (application: Application, id:String?) :  AndroidViewModel(application) {

    private val albumsDetailRepository = AlbumDetailRepository(application)

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
        try {
            viewModelScope.launch (Dispatchers.Default){
                withContext(Dispatchers.IO){
                    var data = albumsDetailRepository.refreshData(id)
                    _albums.postValue(data)
                }
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
            }
        }
        catch (e:Exception){
            _eventNetworkError.value = true
        }

    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }


    fun comentarAlbum(idAlbum:String?, comentario:String, callback: (JSONObject)->Unit, onError: (VolleyError)->Unit){

        var collectorComentario = CollectorComentario(id = 1)
        var comentario = Comentario(description = comentario,rating = 5, collector = collectorComentario);

        var gson = Gson()
        var jsonString = gson.toJson(comentario)

        var dataComentario = JSONObject(jsonString);

        albumsDetailRepository.comentarAlbum(idAlbum,dataComentario,callback,onError)

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
