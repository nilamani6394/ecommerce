package com.ecommerce.ecommerce.service

import com.ecommerce.ecommerce.Repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service
class MyUserDetailsService : UserDetailsService {
    @Autowired
    private lateinit var userRepository: UserRepository


    @Throws(UsernameNotFoundException::class)
     override fun loadUserByUsername(username: String): UserDetails {
         val user= userRepository.findByUsername(username)
             ?: throw UsernameNotFoundException("Cannot find this username$username")
        return User(user.username, user.password, ArrayList())
     }

}
