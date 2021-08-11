package com.ecommerce.ecommerce.model.req

import com.ecommerce.ecommerce.entity.Category

data class ReqProduct(
    val name:String="",
    val tags:String="",
    val price:Int,
    val description:String="",
    val category_id:Category
)
