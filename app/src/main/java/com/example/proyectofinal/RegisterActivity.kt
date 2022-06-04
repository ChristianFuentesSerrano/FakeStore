package com.example.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectofinal.databinding.ActivityRegisterBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlin.random.Random

private lateinit var binding: ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val sendButton = binding.guardar

        val database = Firebase.database
        val myRef = database.reference

        sendButton.setOnClickListener{
            val id = Random(System.nanoTime()).nextInt().toString().replace('-',' ').trim()
            val nombre = binding.nombre.text.toString().trim()
            val apodo = binding.apodo.text.toString().trim()
            val email = binding.correo.text.toString().trim()
            val telefono = binding.telefono.text.toString().trim()
            val nivel = 1
            val puntos = 0
            val ptssigNivel = 500
            val cantidadCarrito = 0
            myRef.child("usuarios").child("U-"+id).setValue(User(nombre, apodo, email, telefono, nivel, puntos, ptssigNivel,cantidadCarrito))
            startActivity(Intent(this@RegisterActivity, TiendaActivity::class.java))
            finish()
        }
    }
}