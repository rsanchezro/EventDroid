package com.example.eventdroid.data

import android.net.Uri
import kotlinx.serialization.Serializable

@Serializable
data class Evento(var titulo:String,var imagen:String,var fecha:Long,var enlace:String,val opiniones: List<Int> =emptyList<Int>())
