package com.ecommerce.ecommerce.model.res

data class JwtResponseOfUser(
    val name:String="",
    val email:String="",
    val password:String="",
    val token:String="",
)
