package com.ecommerce.ecommerce.controller

import com.ecommerce.ecommerce.Repository.UserRepository
import com.ecommerce.ecommerce.entity.User
import com.ecommerce.ecommerce.model.req.ReqUser
import com.ecommerce.ecommerce.model.res.ResUser
import com.ecommerce.ecommerce.model.res.ResponseMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController {
    @Autowired
    private lateinit var userRepository: UserRepository
    @PostMapping("/signup")
    fun signup(@ModelAttribute request:ReqUser):ResponseEntity<*>{
        /*val  user=userRepository.searchByEmail(request.email)
        if (user == request.email){
            return ResponseEntity(ResponseMessage("This email Address already registered"),HttpStatus.BAD_REQUEST)
        }else{*/
            val newUser=User(
                name = request.name,email = request.email,
                password = request.password,contact = request.contact
            )
            val saveUser=userRepository.save(newUser)
            val resUser=ResUser(saveUser.name,saveUser.email,saveUser.contact)
            return ResponseEntity(resUser,HttpStatus.CREATED)
        /*}*/
    }
}