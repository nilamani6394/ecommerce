package com.ecommerce.ecommerce.Repository

import com.ecommerce.ecommerce.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface UserRepository :JpaRepository<User,Long> {
    @Query("select u from User u where u.username like %:keyword%")
    fun findByEmail(@Param("keyword") username: String):String

    fun findByUsername(username: String):User

}