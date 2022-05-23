package com.example.mycatalog.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ProductsAPI {

    /*Conexiones que se van a realizar.
    Productos: https://www.serverbpw.com/cm/2022-2/products.php
    Detalles: https://www.serverbpw.com/cm/2022-2/product_detail.php?id=8487
     */

    @GET
    fun getProducts(
        @Url url:String?
    ): Call<List<Product>>

    @GET("cm/2022-2/product_detail.php")
    fun getProductDetail(
        @Query("id") id: String?
    ):Call<ProductDetails>
}