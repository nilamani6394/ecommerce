package com.ecommerce.ecommerce.model.req


data class ReqUser(
    val name:String="",
    val username:String="",
    val password:String="",
    val otp:Int=0,
)
