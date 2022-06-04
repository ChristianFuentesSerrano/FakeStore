package com.example.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectofinal.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        val joinNowButton = binding.mainJoinNowBtn
        val loginButton = binding.mainLoginBtn

        joinNowButton.setOnClickListener{
            startActivity(Intent(this@MainActivity, RegisterActivity::class.java))
            finish()
        }

        loginButton.setOnClickListener{
            startActivity(Intent(this@MainActivity, TiendaActivity::class.java))
            finish()
        }
    }
}