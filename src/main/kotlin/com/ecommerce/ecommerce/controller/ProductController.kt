package com.ecommerce.ecommerce.controller

import com.ecommerce.ecommerce.Repository.ProductRepository
import com.ecommerce.ecommerce.entity.Category
import com.ecommerce.ecommerce.entity.Product
import com.ecommerce.ecommerce.model.req.ReqProduct
import com.ecommerce.ecommerce.model.res.ResponseMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/product")
class ProductController {
    @Autowired
    private lateinit var productRepository:ProductRepository
    @PostMapping("/addProduct")
    fun addProduct(@ModelAttribute request:ReqProduct):ResponseEntity<*>{
        val newProduct= Product(
            name = request.name,
            tags=request.tags,
            price = request.price,
            description = request.description,
            category = request.category_id
        )
        val product= productRepository.save(newProduct)
        return ResponseEntity(product,HttpStatus.CREATED)
    }
    /** This Api Helps to find the product by name*/
    @GetMapping("/{name}")
    fun getProductByName(@PathVariable(value ="name") name:String):ResponseEntity<*>{
        val latestProduct=productRepository.searchProduct(name)
       return if (latestProduct.isNullOrEmpty()){
            ResponseEntity(ResponseMessage("No product Found"),HttpStatus.NOT_FOUND)
        }else{
            val requireProduct=productRepository.searchProduct(name)
             ResponseEntity(requireProduct,HttpStatus.FOUND)
        }
    }
}

/*@Throws(Exception::class)
fun run(vararg args: String?) {
    val post = Post("one to many mapping using JPA and hibernate", "one to many mapping using JPA and hibernate")
    val comment1 = Comment("Very useful")
    val comment2 = Comment("informative")
    val comment3 = Comment("Great post")
    post.getComments().add(comment1)
    post.getComments().add(comment2)
    post.getComments().add(comment3)
    this.postRepository.save(post)
}*/

