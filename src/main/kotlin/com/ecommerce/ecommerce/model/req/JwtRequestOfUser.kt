package com.ecommerce.ecommerce.model.req

data class JwtRequestOfUser(
    val name:String="",
    val email:String="",
    val password:String="",
    val otp:Int=0
)
