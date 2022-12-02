package com.miso.vinilos.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.android.volley.VolleyError
import com.miso.vinilos.models.Album
import com.miso.vinilos.repositories.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import  com.google.gson.Gson

class AlbumCreateViewModel(application: Application) :  AndroidViewModel(application) {

    private val albumsRepository = AlbumRepository(application)

    private val _albums = MutableLiveData<List<Album>>()

    val albums: LiveData<List<Album>>
        get() = _albums

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
        try {
            viewModelScope.launch (Dispatchers.Default){
                withContext(Dispatchers.IO){

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

    fun crearAlbum(nombreAlbum:String,urlAlbum:String,fechaAlbum:String,generoAlbum:String,empresaAlbum:String,descripcionAlbum:String,callback: (JSONObject)->Unit, onError: (VolleyError)->Unit): String {

        var respuesta = "";
        var album = Album(
            name = nombreAlbum,
            cover = urlAlbum,
            releaseDate = fechaAlbum,
            description = descripcionAlbum,
            genre = generoAlbum,
            recordLabel = empresaAlbum
        );
        var gson = Gson()
        var jsonString = gson.toJson(album)


        var dataAlbum = JSONObject(jsonString);
        dataAlbum.remove("id")
         albumsRepository.createAlbum(dataAlbum,callback,onError)

        return respuesta;
    }


    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AlbumCreateViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AlbumCreateViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
