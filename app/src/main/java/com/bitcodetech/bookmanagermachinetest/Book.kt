package com.bitcodetech.bookmanagermachinetest

data class Book(
    val title:String,
    val subtitle:String,
    val isbn13:Long,
    val price:String,
    val image:String,
    val url:String
):java.io.Serializable
