package com.miso.vinilos.models

data class Comentario (
    var description:String = "",
    var rating:Int = 0,
    var collector:CollectorComentario = CollectorComentario(),
)

data class CollectorComentario(
    var id:Int = 0
)