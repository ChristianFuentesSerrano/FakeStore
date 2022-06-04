package com.example.proyectofinal.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.proyectofinal.Adapter
import com.example.proyectofinal.MainViewModel
import com.example.proyectofinal.databinding.FragmentHomeBinding
import com.example.proyectofinal.remote.ProductsResponse
import com.example.proyectofinal.remote.RetrofitBuilder
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import kotlin.random.Random

class Home : Fragment() {
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var queue:RequestQueue
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        queue = Volley.newRequestQueue(context)

        val view = binding.root

        getProductsList()

        binding.bSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(busqueda: String?): Boolean {
                binding.bSearch.clearFocus()

                val filtered = JSONArray()

                val url = "https://fakestoreapi.com/products"

                val jsonRequest = JsonArrayRequest(url,  {
                    for (i in 0 until it.length()) {
                        val obj: JSONObject = it.getJSONObject(i)
                        val title = obj.getString("title")
                        if (title.contains(binding.bSearch.query)) {
                            filtered.put(obj)
                        }
                    }
                    binding.rvProductEntries.adapter = Adapter(filtered, mainViewModel)
                },
                    {
                        Log.w("JSONRESPONSE", it.message as String)
                    })

                queue.add(jsonRequest)

                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                Log.d("retrofitresponse", "búsqueda")
                return false
            }
        })

        return binding.root
    }


    fun getProductsList(){
        var myjsonarray = JSONArray()
        for (i in 1..5){
            var jsonobject = JSONObject()
            val product_id = Random.nextInt(0,19)
            val retrofit = RetrofitBuilder.create().getProductById(product_id.toString())
            retrofit.enqueue(object: Callback<ProductsResponse> {
                override fun onResponse(call: Call<ProductsResponse>, response: retrofit2.Response<ProductsResponse>){
                    val resBody = response.body()
                    if(resBody != null){
                        jsonobject.put("id", "${resBody.id}")
                        jsonobject.put("title", "${resBody.title}")
                        jsonobject.put("price", "${resBody.price}")
                        jsonobject.put("description", "${resBody.description}")
                        jsonobject.put("category", "${resBody.category}")
                        jsonobject.put("image", "${resBody.image}")
                        myjsonarray.put(jsonobject)

                        binding.rvProductEntries.layoutManager = LinearLayoutManager(view?.context)
                        binding.rvProductEntries.adapter = Adapter(myjsonarray, mainViewModel)
                    }
                }

                override fun onFailure(call: Call<ProductsResponse>, t: Throwable) {
                    Log.e("retrofitresponse", "error: ${t.message}")
                }
            })
        }
    }

    override fun onStop() {
        super.onStop()
        queue.cancelAll("Stopped")
    }
}