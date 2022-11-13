package com.miso.vinilos.models
import com.miso.vinilos.models.Album
data class Artista (
    val id:Int = -1,
    val name:String = "",
    val image:String = "",
    val description:String = "",
    val birthDate:String = "",
    val albums:List<Album> = emptyList()
)