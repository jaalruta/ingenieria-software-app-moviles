package com.miso.vinilos.repositories
import android.app.Application
import com.android.volley.VolleyError
import com.miso.vinilos.models.Coleccionista
import com.miso.vinilos.network.NetworkServiceAdapter

class ColeccionistaRepository (val application: Application){

    fun refreshData(callback: (List<Coleccionista>)->Unit, onError: (VolleyError)->Unit) {
        NetworkServiceAdapter.getInstance(application).getColeccionistas({
            callback(it)
        },
            onError
        )
    }
}