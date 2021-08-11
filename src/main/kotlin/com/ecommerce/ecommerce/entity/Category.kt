package com.ecommerce.ecommerce.entity

import javax.persistence.*

@Entity
data class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    val id:Long=-1,
    val name:String="",
    @OneToMany(mappedBy = "category")
    val product:List<Product>?
)
