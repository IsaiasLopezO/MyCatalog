package com.example.mycatalog.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.mycatalog.R
import com.example.mycatalog.databinding.ActivityDetallesBinding
import com.example.mycatalog.model.Product
import com.example.mycatalog.model.ProductDetails
import com.example.mycatalog.model.ProductsAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Detalles : AppCompatActivity() {

    private lateinit var binding: ActivityDetallesBinding

    private val BASE_URL = "https://www.serverbpw.com/"
    private val LOGTAG = "LOGS"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val id = bundle?.getString("id","0")

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val productsAPI: ProductsAPI = retrofit.create(ProductsAPI::class.java)
        val call: Call<ProductDetails> = productsAPI.getProductDetail(id)

        call. enqueue(object: Callback<ProductDetails>{
            override fun onResponse(call: Call<ProductDetails>, response: Response<ProductDetails>) {
                with(binding){
                    pbConexion.visibility = View.INVISIBLE

                    tvTitle.text = response.body()?.name
                    Glide.with(this@Detalles)
                        .load(response.body()?.imag_url)
                        .into(ivImage)
                    tvLongDesc.text = response.body()?.desc
                }
            }

            override fun onFailure(call: Call<ProductDetails>, t: Throwable) {
                Log.d(LOGTAG,"Error")
                binding.pbConexion. visibility = View.INVISIBLE
                Toast.makeText(this@Detalles, getString(R.string.no_hay_conexion), Toast.LENGTH_SHORT).show()
            }

        })
    }
}