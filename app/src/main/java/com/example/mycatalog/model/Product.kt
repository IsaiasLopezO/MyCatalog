package com.example.mycatalog.model

import com.google.gson.annotations.SerializedName

class Product {

    //Parametros dentro de @SerializedName deben ser exactamente iguales a como vienen en link
    @SerializedName("id")
    var id:String? = null

    @SerializedName("name")
    var name:String? = null

    @SerializedName("thumbnail_url")
    var thumbnail_url:String? = null

    @SerializedName("price")
    var price:String? = null

    @SerializedName("provider")
    var provider:String? = null

    @SerializedName("delivery")
    var delivery:String? = null

}