package com.ecommerce.ecommerce.controller

import com.ecommerce.ecommerce.Repository.UserRepository
import com.ecommerce.ecommerce.entity.User
import com.ecommerce.ecommerce.model.req.ReqUser
import com.ecommerce.ecommerce.model.res.ResBadRequest
import com.ecommerce.ecommerce.model.res.ResUser
import com.ecommerce.ecommerce.model.res.ResponseMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.random.Random
import kotlin.random.nextInt

@RestController
@RequestMapping("/user")
class UserController {
    @Autowired
    private lateinit var userRepository: UserRepository
    @PostMapping("/signup")
    fun signup(@ModelAttribute request:ReqUser):ResponseEntity<*>{
        val map = hashMapOf<String, String>()
        if (request.name.isEmpty()) {
            map["name"] = "Name cannot be empty"
        }
        if (request.email.isEmpty()) {
            map["email"] = "email cannot be empty"
        }/*else if (request.email.isVal){}*/
        if (request.password.isEmpty()) {
            map["password"] = "password field cannot be empty"
        }
//        if(departmentRepository.searchIdList()?.contains(request.deptId)==true){}
        if (!map.isNullOrEmpty()) {
            return ResponseEntity(ResBadRequest(map), HttpStatus.NOT_ACCEPTABLE)
        }
        val newUser=User(
            name = request.name,
            email = request.email,
            password = request.password,
            otp = Random.nextInt(111111..999999)
        )
        val saveUser=userRepository.save(newUser)
        val resUser=ResUser(saveUser.name,saveUser.email)
        return ResponseEntity(resUser,HttpStatus.OK)
    }
}