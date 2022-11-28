package com.miso.vinilos.models
import com.miso.vinilos.models.Album
data class Coleccionista (
    var id:Int = -1,
    var name:String = "",
    var telephone:String = "",
    var email:String = "",
    var albums: List<Album> = emptyList()
)