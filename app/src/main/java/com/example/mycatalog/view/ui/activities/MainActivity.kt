package com.example.mycatalog.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mycatalog.R
import com.example.mycatalog.databinding.ActivityMainBinding
import com.example.mycatalog.model.Product
import com.example.mycatalog.model.ProductsAPI
import com.example.mycatalog.view.adapter.adaptador
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), adaptador.OnItemListener {

    private val BASE_URL = "https://www.serverbpw.com/"
    private val LOGTAG = "LOGS"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val ProductsAPI: ProductsAPI = retrofit.create(ProductsAPI::class.java)
        val call: Call<List<Product>> = ProductsAPI.getProducts("cm/2022-2/products.php")

        call.enqueue(object: Callback<List<Product>>{
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                //Se tiene una respuesta correcta

                Log.d(LOGTAG, "Respuesta del servidor: ${response.toString()}")
                Log.d(LOGTAG, "Datos: ${response.body().toString()}")
                binding.pbConexion. visibility = View.INVISIBLE

                /*val productTmp:Product
                for (productTmp in response.body()!!){
                    Toast.makeText(this@MainActivity, productTmp.name, Toast.LENGTH_SHORT). show()
                }*/

                val adaptador = adaptador(this@MainActivity, response.body()!!, this@MainActivity)
                val recyclerView = binding.rvMenu
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                recyclerView.adapter = adaptador
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                Log.d(LOGTAG,"Error")
                binding.pbConexion. visibility = View.INVISIBLE
                Toast.makeText(this@MainActivity, getString(R.string.no_hay_conexion), Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onItemClick(product: Product) {
        val parametros = Bundle()
        parametros.putString("id", product.id)
        val intent = Intent(this, Detalles::class.java)
        intent.putExtras(parametros)
        startActivity(intent)
    }
}