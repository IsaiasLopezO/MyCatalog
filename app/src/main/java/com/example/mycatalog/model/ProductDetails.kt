package com.example.mycatalog.model

import com.google.gson.annotations.SerializedName

class ProductDetails {

    //Parametros dentro de @SerializedName deben ser exactamente iguales a como vienen en link
    @SerializedName("name")
    var name:String? = null

    @SerializedName("imag_url")
    var imag_url:String? = null

    @SerializedName("desc")
    var desc:String? = null

}