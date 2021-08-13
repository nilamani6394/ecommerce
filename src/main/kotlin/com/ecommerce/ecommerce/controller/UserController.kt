package com.ecommerce.ecommerce.controller

import com.ecommerce.ecommerce.Repository.UserRepository
import com.ecommerce.ecommerce.entity.User
import com.ecommerce.ecommerce.model.req.ReqUser
import com.ecommerce.ecommerce.model.res.ResBadRequest
import com.ecommerce.ecommerce.model.res.ResUser
import com.ecommerce.ecommerce.service.JwtUserDetailsService
import com.ecommerce.ecommerce.util.JwtTokenUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.*
import kotlin.random.Random
import kotlin.random.nextInt

@RestController
@RequestMapping("/user")
class UserController {
    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var jwtTokenUtil: JwtTokenUtil

    @Autowired
    private lateinit var jwtUserDetailsService: JwtUserDetailsService

    @PostMapping("/signup")
    fun signup(@ModelAttribute request: ReqUser): ResponseEntity<*> {
        val newUser=User(
            name = request.name,
            username = request.username,
            password = request.password,
            otp = Random.nextInt(1111..99999)
        )
        val saveNewUser=userRepository.save(newUser)
        val resUser=ResUser(saveNewUser.name,saveNewUser.username,saveNewUser.otp,saveNewUser
            .token)
        return ResponseEntity(resUser,HttpStatus.OK)
    }


}

