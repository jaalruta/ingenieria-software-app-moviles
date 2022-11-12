package com.miso.vinilos.repositories
import android.app.Application
import com.android.volley.VolleyError
import com.miso.vinilos.models.Album
import com.miso.vinilos.network.NetworkServiceAdapter

class AlbumDetailRepository (val application: Application){

    fun refreshData(callback: (Album)->Unit, onError: (VolleyError)->Unit, id:String?) {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        NetworkServiceAdapter.getInstance(application).getAlbum({
            //Guardar los albumes de la variable it en un almacén de datos local para uso futuro
            callback(it)
        },
            onError,
            id
        )
    }
}