package com.example.proyectofinal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.databinding.UserItemBinding
import com.squareup.picasso.Picasso
import org.json.JSONArray
import org.json.JSONObject

class AdapterUser(private val users: JSONArray): RecyclerView.Adapter<AdapterUser.MainHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterUser.MainHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: AdapterUser.MainHolder, position: Int) {
        holder.render(users.getJSONObject(position))
    }

    override fun getItemCount(): Int = users.length()


    class MainHolder(val binding: UserItemBinding):RecyclerView.ViewHolder(binding.root) {
        fun render(user: JSONObject){
            Picasso.get().load("https://images4.fanpop.com/image/photos/21300000/Chibi-Sora-kingdom-hearts-21309093-850-699.jpg").into(binding.ivImg)
            binding.tvNombre.setText(user.getString("nombre"))
            binding.tvApodo.setText(user.getString("apodo"))
            binding.tvNivel.setText("Nivel: "+(user.getInt("nivel")).toString()+"    ")
            binding.tvpuntosAct.setText("Actuales: "+(user.getInt("puntos")).toString())
            binding.tvpuntosFal.setText("Faltantes: "+(user.getInt("ptssigNivel")).toString())
        }
    }
}