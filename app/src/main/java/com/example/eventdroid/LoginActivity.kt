package com.example.eventdroid

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.eventdroid.data.ProveedorUsuario
import com.example.eventdroid.data.Usuario
import com.example.eventdroid.databinding.ActivityLoginBinding
import com.example.eventdroid.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {
    lateinit var mibinding:ActivityLoginBinding
    var usuario: Usuario?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
       mibinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(mibinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.loginactivity)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


    }


}