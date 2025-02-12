package com.example.eventdroid.data

import android.content.Context
import kotlinx.serialization.json.Json

class ProveedorEvento {
    companion object{



        private var appContext: Context? = null
        private var _eventos: MutableList<Evento>? = null

        fun inicializar(context: Context) {
            appContext = context.applicationContext
        }

        val eventos: MutableList<Evento>
            get() {
                if (_eventos == null) {
                    _eventos = cargarEventosDesdeJson()
                }
                return _eventos!!
            }



        private fun cargarEventosDesdeJson(): MutableList<Evento> {
            return try {

                val context = appContext ?: throw IllegalStateException("GestorEventos no ha sido inicializado.")
                val inputStream = context.assets.open("eventos.json")
                val jsonString = inputStream.bufferedReader().use { it.readText() }
                Json.decodeFromString<List<Evento>>(jsonString).toMutableList()
            } catch (e: Exception) {

                println("Error al cargar los eventos: ${e.message}")
                mutableListOf()
            }
        }
    }
}