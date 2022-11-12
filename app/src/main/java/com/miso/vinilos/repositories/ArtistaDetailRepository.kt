package com.miso.vinilos.repositories
import android.app.Application
import com.android.volley.VolleyError
import com.miso.vinilos.models.Artista
import com.miso.vinilos.network.NetworkServiceAdapter

class ArtistaDetailRepository (val application: Application){

    fun refreshData(callback: (Artista)->Unit, onError: (VolleyError)->Unit, id:String?) {

        NetworkServiceAdapter.getInstance(application).getArtista({

            callback(it)
        },
            onError,
            id
        )
    }
}