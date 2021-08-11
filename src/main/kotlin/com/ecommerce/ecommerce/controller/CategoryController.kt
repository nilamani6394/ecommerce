package com.ecommerce.ecommerce.controller

import com.ecommerce.ecommerce.Repository.CategoryRepository
import com.ecommerce.ecommerce.entity.Category
import com.ecommerce.ecommerce.model.ReqCategory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/category")
class CategoryController {
    @Autowired
    private lateinit var categoryRepository: CategoryRepository
    @PostMapping("/addCategory")
    fun addCategory(@ModelAttribute request:ReqCategory):ResponseEntity<*>{
        val reqCategory= Category(name = request.name,product = null)
       val category= categoryRepository.save(reqCategory)
        return ResponseEntity(category,HttpStatus.CREATED)
    }
}