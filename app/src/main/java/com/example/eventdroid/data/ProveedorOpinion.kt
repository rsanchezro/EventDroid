package com.example.eventdroid.data

import android.content.Context
import kotlinx.serialization.json.Json

class ProveedorOpinion {

    companion object{



        private var appContext: Context? = null
        private var _opiniones: MutableList<Opinion>? = null

        fun inicializar(context: Context) {
            appContext = context.applicationContext
        }

        val opiniones: MutableList<Opinion>
            get() {
                if (_opiniones == null) {
                    _opiniones = cargaropinionesDesdeJson()
                }
                return _opiniones!!
            }



        private fun cargaropinionesDesdeJson(): MutableList<Opinion> {
            return try {

                val context = appContext ?: throw IllegalStateException("Gestoropiniones no ha sido inicializado.")
                val inputStream = context.assets.open("opiniones.json")
                val jsonString = inputStream.bufferedReader().use { it.readText() }
                Json.decodeFromString<List<Opinion>>(jsonString).toMutableList()
            } catch (e: Exception) {

                println("Error al cargar los opiniones: ${e.message}")
                mutableListOf()
            }
        }
    }
    
}