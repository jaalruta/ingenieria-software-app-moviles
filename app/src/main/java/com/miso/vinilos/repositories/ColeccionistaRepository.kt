package com.miso.vinilos.repositories
import android.app.Application
import android.util.Log
import com.android.volley.VolleyError
import com.miso.vinilos.models.Album
import com.miso.vinilos.models.Coleccionista
import com.miso.vinilos.network.CacheManager
import com.miso.vinilos.network.NetworkServiceAdapter

class ColeccionistaRepository (val application: Application){

    suspend fun refreshData(): List<Coleccionista> {
        var potentialResp = CacheManager.getInstance(application.applicationContext).getColeccionistas(-9997)
        if(potentialResp.isEmpty()){
            Log.d("Cache decision", "get from network")
            var coleccionistas = NetworkServiceAdapter.getInstance(application).getColeccionistas()
            CacheManager.getInstance(application.applicationContext).addColeccionistas(-9997, coleccionistas)
            return coleccionistas
        }
        else{
            Log.d("Cache decision", "return ${potentialResp.size} elements from cache")
            return potentialResp
        }
    }
}