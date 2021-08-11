package com.ecommerce.ecommerce.Repository

import com.ecommerce.ecommerce.entity.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<Category,Long> {
}