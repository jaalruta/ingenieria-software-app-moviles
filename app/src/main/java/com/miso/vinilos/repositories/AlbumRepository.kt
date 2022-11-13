package com.miso.vinilos.repositories
import android.app.Application
import com.miso.vinilos.models.Album
import com.miso.vinilos.network.NetworkServiceAdapter
import com.miso.vinilos.network.CacheManager

class AlbumRepository (val application: Application){

    suspend fun refreshData(): List<Album> {
        var potentialResp = CacheManager.getInstance(application.applicationContext).getAlbums(-9999)
        if(potentialResp.isEmpty()){
           // Log.d("Cache decision", "get from network")
            var albums = NetworkServiceAdapter.getInstance(application).getAlbums()
            CacheManager.getInstance(application.applicationContext).addAlbums(-9999, albums)
            return albums
        }
        else{
            //Log.d("Cache decision", "return ${potentialResp.size} elements from cache")
            return potentialResp
        }

    }

}