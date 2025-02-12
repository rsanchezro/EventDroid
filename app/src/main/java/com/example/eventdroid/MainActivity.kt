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


    var objeto_register=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        resultado->
        if(resultado.resultCode==0)
        {
            var datos=resultado.data?.extras?.getString("Usuario")
            if(datos!=null)
            {
                    //Localizo el usuario
                    usuario=ProveedorUsuario.usuarios.find{
                        it.login.equals(datos)
                    }
                //compruebo si es admin
                if(usuario!!.esadmin==1)
                {
                    mibinding.eventoFab.visibility= View.VISIBLE
                }
                //Cambio el logo del item del menu
                mitoolbar.menu.getItem(0).icon=resources.getDrawable(R.drawable.ic_cerrar_sesion,null)
                sesioniniciada=true
                mitoolbar.title=usuario!!.nombre
                //repintar el recyclerview
                miadaptador.establecerUsuario(usuario!!)
            }
        }


    }
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
        Log.i("usuarios",ProveedorUsuario.usuarios.toString())
        Log.i("opiniones",ProveedorOpinion.opiniones.toString())

        //Inicializar Toolbar
        inicializarToolbar()


        //Inicializar Recycler View
        inicializarRecycler()


    }

    private fun inicializarRecycler() {
        mirecycler=mibinding.recyclerviewEventos

        mirecycler.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        miadaptador=AdaptadorEvento(ProveedorEvento.eventos,{posicion->abrir_opiniones()})
        mirecycler.adapter=miadaptador
    }

    private fun abrir_opiniones() {
        if(usuario!=null) {
            var miIntent = Intent(this, OpinionActivity::class.java)
            startActivity(miIntent)
        }
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.id_icono_menu -> {
                if(!sesioniniciada) {
                    val miintent = Intent(this, LoginActivity::class.java)
                    objeto_register.launch(miintent)
                }
                else{
                    item.icon=resources.getDrawable(R.drawable.ic_iniciar_sesion,null)
                    usuario=null
                    sesioniniciada=false
                    mibinding.eventoFab.visibility=View.INVISIBLE
                    mitoolbar.title="EVENDROID"
                    miadaptador.establecerUsuario(null)
                }

            }


        }
        return true
    }
}