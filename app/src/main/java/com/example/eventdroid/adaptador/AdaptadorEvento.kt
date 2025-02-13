package com.example.eventdroid.adaptador

import android.content.ContentResolver
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eventdroid.R
import com.example.eventdroid.data.Evento
import com.example.eventdroid.data.Usuario
import com.example.eventdroid.databinding.ElementoeventoBinding
import java.text.SimpleDateFormat
import java.util.Date

class AdaptadorEvento(val eventos:List<Evento>):RecyclerView.Adapter<AdaptadorEvento.HolderEvento>() {
        private var usuario:Usuario?=null
    inner class HolderEvento(val vista: View):RecyclerView.ViewHolder(vista) {
            val binding_holderevento=ElementoeventoBinding.bind(vista)


    }

    fun establecerUsuario(u:Usuario?)
    {
        this.usuario=u
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderEvento {
        return HolderEvento(
            LayoutInflater.from(parent.context).inflate(R.layout.elementoevento, parent, false)
        )
    }
    override fun getItemCount(): Int=eventos.size

    override fun onBindViewHolder(holder: HolderEvento, position: Int) {


    }
}