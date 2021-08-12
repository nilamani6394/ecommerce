package com.ecommerce.ecommerce.entity

import javax.persistence.*

@Entity
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id:Long=-1,
    val name:String="",
    @Column(unique = true)
    val email:String="",
    val password:String="",
    val otp:Int,
    val token:String=""
)
