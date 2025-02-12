package com.example.eventdroid.data
import kotlinx.serialization.Serializable

@Serializable
data class Opinion(val texto:String,val valoracion:Int,val usuario:String)
