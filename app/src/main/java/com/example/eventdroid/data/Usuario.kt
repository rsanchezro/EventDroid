package com.example.eventdroid.data
import kotlinx.serialization.Serializable

@Serializable
data class Usuario(val login:String,val pass:String,val nombre:String,val foto:String,val esadmin:Int,val eventos_suscritos:List<Int>)
