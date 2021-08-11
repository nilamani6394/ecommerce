package com.ecommerce.ecommerce.model.req


data class ReqUser(
    val name:String="",
    val email:String="",
    val password:String="",
    val contact:Int=0,
)
