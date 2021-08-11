package com.ecommerce.ecommerce.entity

import javax.persistence.*

@Entity
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id:Long=-1,
    val name:String="",
    val tags:String="",
    val price:Int=0,
    val description:String="",
    @ManyToOne
    @JoinColumn(name = "category_id")
    val category:Category
)
