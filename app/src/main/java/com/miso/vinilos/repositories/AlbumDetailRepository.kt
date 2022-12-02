package com.miso.vinilos.repositories
import android.app.Application
import com.android.volley.VolleyError
import com.miso.vinilos.models.Album
import com.miso.vinilos.network.CacheManager
import com.miso.vinilos.network.NetworkServiceAdapter
import org.json.JSONObject
import java.text.SimpleDateFormat

class AlbumDetailRepository (val application: Application){

    suspend fun refreshData( id:String?): Album {
        var idAlbum = id?:"0"

        var potentialResp = CacheManager.getInstance(application.applicationContext).getAlbum(idAlbum.toInt())
        if(potentialResp.id<0){
            //Log.d("Cache decision", "get from network")
            var album =  NetworkServiceAdapter.getInstance(application).getAlbum(id)
            CacheManager.getInstance(application.applicationContext).addAlbum(album.id, album)
            return album
        }
        else{
            //Log.d("Cache decision", "return ${potentialResp.id} album id from cache")
            return potentialResp
        }

    }

    fun comentarAlbum(idAlbum: String?,datosComentario:JSONObject, callback: (JSONObject)->Unit, onError: (VolleyError)->Unit): String {

        NetworkServiceAdapter.getInstance(application).postComentario(idAlbum,datosComentario,{
            callback(it)
        },
            onError
        )

        return "OK";
    }

}