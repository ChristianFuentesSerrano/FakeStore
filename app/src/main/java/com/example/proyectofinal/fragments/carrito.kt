package com.example.proyectofinal.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.example.proyectofinal.AdapterCarrito
import com.example.proyectofinal.MainViewModel
import com.example.proyectofinal.databinding.FragmentCarritoBinding
import org.json.JSONArray
import org.json.JSONObject

class Carrito : Fragment() {
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var queue:RequestQueue
    private lateinit var binding: FragmentCarritoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentCarritoBinding.inflate(inflater, container, false)
        queue = Volley.newRequestQueue(context)

        getCarrito()

        return binding.root
    }

    fun getCarrito(){
        var myjsonarray = JSONArray()

        mainViewModel.getProducts()
        mainViewModel.savedProducts.observe(viewLifecycleOwner){ productsList->
            if(!productsList.isNullOrEmpty()){
                for(product in productsList){
                    var jsonobject = JSONObject()
                    jsonobject.put("id", "${product.id}")
                    jsonobject.put("title", "${product.title}")
                    jsonobject.put("price", "${product.price}")
                    jsonobject.put("description", "${product.description}")
                    jsonobject.put("category", "${product.category}")
                    jsonobject.put("image", "${product.image}")
                    myjsonarray.put(jsonobject)

                    binding.rvCarritoEntries.layoutManager = LinearLayoutManager(view?.context)
                    binding.rvCarritoEntries.adapter = AdapterCarrito(myjsonarray, mainViewModel)
                }
            } else {
                Log.d("thesearetheusers", "null or empty")
            }
        }
    }

    override fun onStop() {
        super.onStop()
        queue.cancelAll("Stopped")
    }
}