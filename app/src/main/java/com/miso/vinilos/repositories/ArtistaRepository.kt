package com.miso.vinilos.repositories
import android.app.Application
import android.util.Log
import com.android.volley.VolleyError
import com.miso.vinilos.models.Album
import com.miso.vinilos.models.Artista
import com.miso.vinilos.network.CacheManager
import com.miso.vinilos.network.NetworkServiceAdapter

class ArtistaRepository (val application: Application){


    suspend fun refreshData(): List<Artista> {
        var potentialResp = CacheManager.getInstance(application.applicationContext).getArtistas(-9998)
        if(potentialResp.isEmpty()){
            Log.d("Cache decision", "get from network")
            var artistas = NetworkServiceAdapter.getInstance(application).getArtistas()
            CacheManager.getInstance(application.applicationContext).addArstista(-9998, artistas)
            return artistas
        }
        else{
            Log.d("Cache decision", "return ${potentialResp.size} elements from cache")
            return potentialResp
        }
    }
}