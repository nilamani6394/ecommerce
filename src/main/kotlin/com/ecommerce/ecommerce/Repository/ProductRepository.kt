package com.ecommerce.ecommerce.Repository

import com.ecommerce.ecommerce.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ProductRepository : JpaRepository<Product,Long> {
    @Query("select p from Product p where p.name like %:keyword%")
    fun searchProduct(@Param("keyword") name: String): List<Product>?
}