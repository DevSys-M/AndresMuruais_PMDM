package com.example.t8_examen.model

import java.io.Serializable

data class Producto(var id:Int?=null,var price:Int?=null,var stock:Int?=null,var thumbnail: String?=null, var title: String?=null):
    Serializable