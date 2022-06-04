package com.example.proyectofinal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.database.Product
import com.example.proyectofinal.databinding.ProductItemCarritoBinding
import com.squareup.picasso.Picasso
import org.json.JSONArray
import org.json.JSONObject
import kotlin.random.Random

class AdapterCarrito(private val productos: JSONArray, private val viewModel: MainViewModel): RecyclerView.Adapter<AdapterCarrito.MainHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterCarrito.MainHolder {
        val binding = ProductItemCarritoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHolder(binding, viewModel)
    }

    override fun onBindViewHolder(holder: AdapterCarrito.MainHolder, position: Int) {
        holder.render(productos.getJSONObject(position))
    }

    override fun getItemCount(): Int = productos.length()


    class MainHolder(val binding: ProductItemCarritoBinding, val viewModel: MainViewModel):RecyclerView.ViewHolder(binding.root) {
        fun render(producto: JSONObject){
            Picasso.get().load(producto.getString("image")).into(binding.ivImg)
            binding.tvArticulo.setText(producto.getString("title"))
            binding.tvPrecio.setText("$"+producto.getString("price"))
            binding.tvCategoria.setText(producto.getString("category"))
            binding.tvDescripcion.setText(producto.getString("description"))
            binding.tvCalificacion.setText(Random.nextInt(2,5).toString())
            binding.btnCarrito.setOnClickListener{
                viewModel.deleteProduct(Product(
                    producto.getInt("id"),
                    producto.getString("title"),
                    producto.getDouble("price"),
                    producto.getString("description"),
                    producto.getString("category"),
                    producto.getString("image")
                ))
            }
        }
    }
}