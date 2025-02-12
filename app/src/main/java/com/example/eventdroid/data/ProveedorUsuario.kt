package com.example.eventdroid.data

import android.content.Context
import kotlinx.serialization.json.Json

class ProveedorUsuario {
    companion object{



        private var appContext: Context? = null
        private var _usuarios: MutableList<Usuario>? = null

        fun inicializar(context: Context) {
            appContext = context.applicationContext
        }

        val usuarios: MutableList<Usuario>
            get() {
                if (_usuarios == null) {
                    _usuarios = cargarUsuariosDesdeJson()
                }
                return _usuarios!!
            }



        private fun cargarUsuariosDesdeJson(): MutableList<Usuario> {
            return try {

                val context = appContext ?: throw IllegalStateException("GestorUsuarios no ha sido inicializado.")
                val inputStream = context.assets.open("usuarios.json")
                val jsonString = inputStream.bufferedReader().use { it.readText() }
                Json.decodeFromString<List<Usuario>>(jsonString).toMutableList()
            } catch (e: Exception) {

                println("Error al cargar los usuarios: ${e.message}")
                mutableListOf()
            }
        }
    }
}