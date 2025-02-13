package com.example.eventdroid

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eventdroid.adaptador.AdaptadorEvento
import com.example.eventdroid.data.ProveedorEvento
import com.example.eventdroid.data.ProveedorOpinion
import com.example.eventdroid.data.ProveedorUsuario
import com.example.eventdroid.data.Usuario
import com.example.eventdroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var mibinding: ActivityMainBinding
    lateinit var mitoolbar: Toolbar
    lateinit var mirecycler:RecyclerView
    lateinit var miadaptador:AdaptadorEvento
    var usuario: Usuario?=null
    var sesioniniciada:Boolean=false




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mibinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mibinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Inicializo los contextos de los Proveedores
        ProveedorEvento.inicializar(this )
        ProveedorOpinion.inicializar(this)
        ProveedorUsuario.inicializar(this)

        //Inicializar Toolbar
        inicializarToolbar()


        //Inicializar Recycler View
        inicializarRecycler()


    }

    private fun inicializarRecycler() {

    }



    private fun inicializarToolbar() {
        mitoolbar = mibinding.toolbar
        mitoolbar.setLogo(R.drawable.ic_logodef)
        mitoolbar.setTitle(R.string.app_name)
        mitoolbar.titleMarginStart = 128

        mitoolbar.setTitleTextColor(resources.getColor(R.color.primaryTextColor, null))
        setSupportActionBar(mitoolbar)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_login, menu)
        return true
    }


}