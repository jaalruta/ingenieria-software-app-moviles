package com.miso.vinilos.network
import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.miso.vinilos.models.Album
import com.miso.vinilos.models.Artista
import com.miso.vinilos.models.Coleccionista
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class NetworkServiceAdapter constructor(context: Context) {
    companion object{
        const val BASE_URL= "https://vinilos-grupo16.herokuapp.com/"
        var instance: NetworkServiceAdapter? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: NetworkServiceAdapter(context).also {
                    instance = it
                }
            }
    }
    private val requestQueue: RequestQueue by lazy {
        // applicationContext keeps you from leaking the Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }

    suspend fun getAlbums()= suspendCoroutine<List<Album>>{ cont ->
        requestQueue.add(getRequest("albums",
            Response.Listener<String> { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Album>()
                var item:JSONObject? = null
                    for (i in 0 until resp.length()) {
                    item = resp.getJSONObject(i)
                    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    val formatter = SimpleDateFormat("yyyy")
                    val fecha = formatter.format(parser.parse(item.getString("releaseDate")))

                    list.add(i, Album(id = item.getInt("id"),name = item.getString("name"), cover = item.getString("cover"), recordLabel = item.getString("recordLabel"), releaseDate = fecha, genre = item.getString("genre"), description = item.getString("description")))
                }
                cont.resume(list)
            },
            Response.ErrorListener {
                cont.resumeWithException(it)
            }))
    }

    suspend fun getAlbum(id:String?)= suspendCoroutine<Album>{ cont ->
        requestQueue.add(getRequest("albums/"+id,
            Response.Listener<String> { response ->
                val resp = JSONObject(response)
                lateinit var album: Album
                val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                val formatter = SimpleDateFormat("yyyy")
                val fecha = formatter.format(parser.parse(resp.getString("releaseDate")))
                album = Album(id = resp.getInt("id"),name = resp.getString("name"), cover = resp.getString("cover"), recordLabel = resp.getString("recordLabel"), releaseDate = fecha, genre = resp.getString("genre"), description = resp.getString("description"))

                cont.resume(album)
            },
            Response.ErrorListener {
                cont.resumeWithException(it)
            }))
    }

    suspend fun getArtistas()= suspendCoroutine<List<Artista>>{ cont ->
        requestQueue.add(getRequest("musicians",
            Response.Listener<String> { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Artista>()
                var albumList = mutableListOf<Album>();
                var item:JSONObject? = null
                var datosAlbum:JSONArray? = null
                for (i in 0 until resp.length()) {
                    item = resp.getJSONObject(i)
                    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    val formatter = SimpleDateFormat("yyyy")
                    val fecha = formatter.format(parser.parse(item.getString("birthDate")))
                    datosAlbum = item.getJSONArray("albums")
                    for(x in 0 until datosAlbum.length())
                    {
                        var album = Album(
                            id = datosAlbum.getJSONObject(x).getInt("id"),
                            name = datosAlbum.getJSONObject(x).getString("id"),
                            cover = datosAlbum.getJSONObject(x).getString("cover"),
                            releaseDate = datosAlbum.getJSONObject(x).getString("releaseDate"),
                            description = datosAlbum.getJSONObject(x).getString("description"),
                            genre = datosAlbum.getJSONObject(x).getString("genre"),
                            recordLabel = datosAlbum.getJSONObject(x).getString("recordLabel")
                        )
                        albumList.add(album)
                    }

                    list.add(i,
                        Artista(
                            id = item.getInt("id"),
                            name = item.getString("name"),
                            image = item.getString("image"),
                            description = item.getString("description"),
                            birthDate = fecha,
                            albums = albumList
                        ))
                }
                cont.resume(list)
            },
            Response.ErrorListener {
                cont.resumeWithException(it)
            }))
    }

    suspend fun getArtista(id:String?)= suspendCoroutine<Artista>{ cont ->
        requestQueue.add(getRequest("musicians/"+id,
            Response.Listener<String> { response ->
                val resp = JSONObject(response)
                lateinit var artista : Artista
                var albumList = mutableListOf<Album>();

                val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                val formatter = SimpleDateFormat("yyyy")
                val fecha = formatter.format(parser.parse(resp.getString("birthDate")))

                val datosAlbum = resp.getJSONArray("albums")
                for(x in 0 until datosAlbum.length())
                {
                    val releaseDate = formatter.format(parser.parse(datosAlbum.getJSONObject(x).getString("releaseDate")))
                    var album = Album(
                        id = datosAlbum.getJSONObject(x).getInt("id"),
                        name = datosAlbum.getJSONObject(x).getString("name"),
                        cover = datosAlbum.getJSONObject(x).getString("cover"),
                        releaseDate = releaseDate,
                        description = datosAlbum.getJSONObject(x).getString("description"),
                        genre = datosAlbum.getJSONObject(x).getString("genre"),
                        recordLabel = datosAlbum.getJSONObject(x).getString("recordLabel")
                    )
                    albumList.add(album)
                }

                artista=  Artista(
                    id = resp.getInt("id"),
                    name = resp.getString("name"),
                    image = resp.getString("image"),
                    description = resp.getString("description"),
                    birthDate = fecha,
                    albums = albumList
                )

                cont.resume(artista)
            },
            Response.ErrorListener {
                cont.resumeWithException(it)
            }))
    }

    suspend fun getColeccionistas()= suspendCoroutine<List<Coleccionista>>{ cont ->
        requestQueue.add(getRequest("collectors",
            Response.Listener<String> { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Coleccionista>()
                var item:JSONObject? = null
                for (i in 0 until resp.length()) {
                    item = resp.getJSONObject(i)
                    list.add(i, Coleccionista(id = item.getInt("id"),name = item.getString("name"), telephone = item.getString("telephone"), email = item.getString("email")))
                }
                cont.resume(list)
            },
            Response.ErrorListener {
                cont.resumeWithException(it)
            }))
    }


    private fun getRequest(path:String, responseListener: Response.Listener<String>, errorListener: Response.ErrorListener): StringRequest {
        return StringRequest(Request.Method.GET, BASE_URL+path, responseListener,errorListener)
    }
    private fun postRequest(path: String, body: JSONObject,  responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ):JsonObjectRequest{
        return  JsonObjectRequest(Request.Method.POST, BASE_URL+path, body, responseListener, errorListener)
    }
    private fun putRequest(path: String, body: JSONObject,  responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ):JsonObjectRequest{
        return  JsonObjectRequest(Request.Method.PUT, BASE_URL+path, body, responseListener, errorListener)
    }
}