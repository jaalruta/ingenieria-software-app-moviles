package com.miso.vinilos.network
import android.content.Context
import com.miso.vinilos.models.Album
import com.miso.vinilos.models.Artista
import com.miso.vinilos.models.Coleccionista

class CacheManager(context: Context) {
    companion object{
        var instance: CacheManager? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: CacheManager(context).also {
                    instance = it
                }
            }
    }
    private var albums: HashMap<Int, List<Album>> = hashMapOf()
    private var album: HashMap<Int, Album> = hashMapOf()


    private var artistas: HashMap<Int, List<Artista>> = hashMapOf()
    private var artista: HashMap<Int, Artista> = hashMapOf()

    private var coleccionistas: HashMap<Int, List<Coleccionista>> = hashMapOf()
    private var coleccionista: HashMap<Int, Coleccionista> = hashMapOf()


    private var coleccionistaDetail: HashMap<Int, Coleccionista> = hashMapOf()

    fun addAlbums(albumId: Int, data: List<Album>){
        if (!albums.containsKey(albumId)){
            albums[albumId] = data
        }
    }
    fun deleteAlbums(albumId: Int)
    {
        albums.clear()
    }
    fun getAlbums(albumId: Int) : List<Album>{
        return if (albums.containsKey(albumId)) albums[albumId]!! else listOf<Album>()
    }


    fun addAlbum(albumId: Int, data: Album){
        if (!album.containsKey(albumId)){
            album[albumId] = data
        }
    }
    fun getAlbum(albumId: Int) : Album {
        return if (album.containsKey(albumId))
            album[albumId]!!
        else
            Album()
    }

    fun addArstista(artistaId: Int, data: List<Artista>){
        if (!artistas.containsKey(artistaId)){
            artistas[artistaId] = data
        }
    }
    fun getArtistas(artistaId: Int) : List<Artista>{
        return if (artistas.containsKey(artistaId)) artistas[artistaId]!! else listOf<Artista>()
    }

    fun addArtista(artistaId: Int, data: Artista){
        if (!artista.containsKey(artistaId)){
            artista[artistaId] = data
        }
    }
    fun getArtista(artistaId: Int) : Artista {
        return if (artista.containsKey(artistaId))
            artista[artistaId]!!
        else
            Artista()
    }

    fun addColeccionistas(coleccionistaId: Int, data: List<Coleccionista>){
        if (!coleccionistas.containsKey(coleccionistaId)){
            coleccionistas[coleccionistaId] = data
        }
    }
    fun getColeccionistas(coleccionistaId: Int) : List<Coleccionista>{
        return if (coleccionistas.containsKey(coleccionistaId)) coleccionistas[coleccionistaId]!! else listOf<Coleccionista>()
    }

    fun addColeccionista(coleccionistaId: Int, data: Coleccionista){
        if (!coleccionista.containsKey(coleccionistaId)){
            coleccionista[coleccionistaId] = data
        }
    }
    fun getColeccionista(coleccionistaId: Int) : Coleccionista {
        return if (coleccionista.containsKey(coleccionistaId))
            coleccionista[coleccionistaId]!!
        else
            Coleccionista()
    }

    fun getColeccionistaDetail(coleccionistaId: Int) : Coleccionista {
        return if (coleccionistaDetail.containsKey(coleccionistaId))
            coleccionistaDetail[coleccionistaId]!!
        else
            Coleccionista()
    }

    fun addColeccionistaDetail(coleccionistaId: Int, data: Coleccionista){
        if (!coleccionistaDetail.containsKey(coleccionistaId)){
            coleccionistaDetail[coleccionistaId] = data
        }
    }

}