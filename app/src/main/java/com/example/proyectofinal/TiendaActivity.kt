package com.example.proyectofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import com.example.proyectofinal.databinding.ActivityTiendaBinding
import com.example.proyectofinal.fragments.Carrito
import com.example.proyectofinal.fragments.Home
import com.example.proyectofinal.fragments.Perfil

private lateinit var binding: ActivityTiendaBinding

@Suppress("DEPRECATION")
class TiendaActivity : AppCompatActivity() {

    lateinit var toolbar: ActionBar

    private val homeFragment = Home()
    private val carritoFragment = Carrito()
    private val perfilFragment = Perfil()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tienda)

        binding = ActivityTiendaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        replaceFragment(homeFragment)

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(homeFragment)
                R.id.cart -> replaceFragment(carritoFragment)
                R.id.profile -> replaceFragment(perfilFragment)
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment){
        if(fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }
}