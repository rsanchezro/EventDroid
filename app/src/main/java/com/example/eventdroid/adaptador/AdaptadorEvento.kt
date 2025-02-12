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

class AdaptadorEvento(val eventos:List<Evento>,onclickElemento:(Int)->Unit):RecyclerView.Adapter<AdaptadorEvento.HolderEvento>() {
        private var usuario:Usuario?=null
    inner class HolderEvento(val vista: View):RecyclerView.ViewHolder(vista) {
            val binding_holderevento=ElementoeventoBinding.bind(vista)
        fun render(posicion:Int)
        {
            val e=eventos.get(posicion)

          //  val miuri=Uri.parse("${ContentResolver.SCHEME_ANDROID_RESOURCE}://${itemView.context.packageName}/${e.imagen}");
            binding_holderevento.imagenevento.setImageURI(Uri.parse(e.imagen))
            binding_holderevento.tituloeventotv.text=e.titulo
            binding_holderevento.fechaEventotv.text= SimpleDateFormat("dd/MM/yyyy").format(Date(e.fecha*1000)).toString()
            if(usuario!=null)
            {
                //Hay que visibilizar el elemento suscrito
                binding_holderevento.suscribirseswitch.visibility=View.VISIBLE
                usuario!!.eventos_suscritos.let {
                    binding_holderevento.suscribirseswitch.isChecked=it.find { it==posicion }!=null
                }


            }
            else
            {
                binding_holderevento.suscribirseswitch.visibility=View.INVISIBLE

            }

        }

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
       holder.render(position)

    }
}