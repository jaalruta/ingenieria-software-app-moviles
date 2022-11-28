package com.miso.vinilos.repositories
import android.app.Application
import com.miso.vinilos.models.Coleccionista
import com.miso.vinilos.network.CacheManager
import com.miso.vinilos.network.NetworkServiceAdapter

class ColeccionistaDetailRepository (val application: Application){

    suspend fun refreshData( id:String?): Coleccionista {
        var idColeccionista = id?:"0"

        var potentialResp = CacheManager.getInstance(application.applicationContext).getColeccionistaDetail(idColeccionista.toInt())
        if(potentialResp.id<0){
            //Log.d("Cache decision", "get from network")
            var coleccionista =  NetworkServiceAdapter.getInstance(application).getColeccionistaDetail(id)
            CacheManager.getInstance(application.applicationContext).addColeccionistaDetail(coleccionista.id, coleccionista)
            return coleccionista
        }
        else{
            //Log.d("Cache decision", "return ${potentialResp.id} artista id from cache")
            return potentialResp
        }

    }
}