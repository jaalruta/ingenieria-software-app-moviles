package com.miso.vinilos.repositories
import android.app.Application
import android.util.Log
import com.android.volley.VolleyError
import com.miso.vinilos.models.Album
import com.miso.vinilos.models.Artista
import com.miso.vinilos.network.CacheManager
import com.miso.vinilos.network.NetworkServiceAdapter

class ArtistaDetailRepository (val application: Application){

    suspend fun refreshData( id:String?): Artista {
        var idArtista = id?:"0"

        var potentialResp = CacheManager.getInstance(application.applicationContext).getArtista(idArtista.toInt())
        if(potentialResp.id<0){
            Log.d("Cache decision", "get from network")
            var artista =  NetworkServiceAdapter.getInstance(application).getArtista(id)
            CacheManager.getInstance(application.applicationContext).addArtista(artista.id, artista)
            return artista
        }
        else{
            Log.d("Cache decision", "return ${potentialResp.id} artista id from cache")
            return potentialResp
        }

    }
}