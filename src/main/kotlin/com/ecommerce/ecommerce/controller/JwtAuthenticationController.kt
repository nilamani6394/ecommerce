package com.ecommerce.ecommerce.controller

import com.ecommerce.ecommerce.model.req.JwtRequestOfUser
import com.ecommerce.ecommerce.model.res.JwtResponseOfUser
import com.ecommerce.ecommerce.service.MyUserDetailsService
import com.ecommerce.ecommerce.util.JwtTokenUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*


@RestController
class JwtAuthenticationController {
    @Autowired
    private lateinit var authenticationManager: AuthenticationManager
    @Autowired
    private lateinit var jwtTokenUtil: JwtTokenUtil
    @Autowired
    private lateinit var jwtUserDetailsService: MyUserDetailsService

    @Throws(Exception::class)
    @PostMapping("/authenticate")
    fun createAuthenticationToken(@ModelAttribute request:JwtRequestOfUser):ResponseEntity<*>{
        authenticate(request.email,request.password)
        val userDetails: UserDetails =jwtUserDetailsService
            .loadUserByUsername(request.email)
        val token: String? =jwtTokenUtil.generateToken(userDetails)
        return ResponseEntity.ok<Any>(token?.let { JwtResponseOfUser(it) })
    }

    private fun authenticate(email:String,password: String) {
        try {
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(email,password))
        }catch (e:DisabledException){
            throw Exception("USER_DISABLE",e)
        }catch (e:BadCredentialsException){
            throw Exception("INVALID_CREDENTIALS",e)
        }
    }

}
