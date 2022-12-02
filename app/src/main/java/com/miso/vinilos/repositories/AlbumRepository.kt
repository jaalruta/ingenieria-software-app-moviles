package com.miso.vinilos.repositories
import android.app.Application
import com.android.volley.VolleyError
import com.miso.vinilos.models.Album
import com.miso.vinilos.network.NetworkServiceAdapter
import com.miso.vinilos.network.CacheManager
import org.json.JSONObject
import java.text.SimpleDateFormat

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

     fun createAlbum(datosAlbum:JSONObject,callback: (JSONObject)->Unit, onError: (VolleyError)->Unit): String {

        NetworkServiceAdapter.getInstance(application).postAlbum(datosAlbum,{
            var albums = CacheManager.getInstance(application.applicationContext).getAlbums(-9999)
            val parser = SimpleDateFormat("yyyy-MM-dd")
            val formatter = SimpleDateFormat("yyyy")
            val fecha = formatter.format(parser.parse(it.getString("releaseDate")))
            val newList = albums.toMutableList()
            newList.add(Album(id = it.getInt("id"),name = it.getString("name"), cover = it.getString("cover"), recordLabel = it.getString("recordLabel"), releaseDate = fecha, genre = it.getString("genre"), description = it.getString("description")))
            CacheManager.getInstance(application.applicationContext).deleteAlbums(-9999)

            CacheManager.getInstance(application.applicationContext).addAlbums(-9999, newList)
            callback(it)
        },
            onError
        )

        return "OK";
    }


}