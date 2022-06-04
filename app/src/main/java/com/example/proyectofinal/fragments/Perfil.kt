package com.example.proyectofinal.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.proyectofinal.AdapterUser
import com.example.proyectofinal.databinding.FragmentPerfilBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.json.JSONArray
import org.json.JSONObject

class Perfil : Fragment() {
    private lateinit var binding: FragmentPerfilBinding
    private val database = Firebase.database
    private val myRef = database.reference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPerfilBinding.inflate(inflater, container, false)

        val list :MutableList<String> = ArrayList()

        myRef.child("usuarios").get().addOnSuccessListener {
            val myjson = JSONObject(it.value.toString())
            val namesArray = myjson.names()
            for(i in 0..namesArray.length()-1){
                list.add(namesArray[i].toString())
            }
            val adapter :ArrayAdapter<String> = ArrayAdapter(this.requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, list)
            binding.spinnerUser.adapter = adapter
        }

        binding.spinnerUser.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val item : String = list[position]

                loadUserRV(item)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}

        }

        return binding.root
    }

    fun loadUserRV(item: String){
        myRef.child("usuarios").child(item).get().addOnSuccessListener {
            val myjson = JSONObject(it.value.toString())
            val jsonArray = JSONArray()
            jsonArray.put(myjson)
            binding.rvUserEntry.adapter = AdapterUser(jsonArray)
        }
    }
}