package com.ecommerce.ecommerce.service

import com.ecommerce.ecommerce.Repository.UserRepository
import com.ecommerce.ecommerce.entity.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class JwtUserDetailsService : UserDetailsService {
    @Autowired
    private lateinit var userRepository: UserRepository
    @Autowired
    private lateinit var user:User

    @Throws(UsernameNotFoundException::class)
     override fun loadUserByUsername(username: String): UserDetails {
         user=userRepository.findByUsername(username)
         return org.springframework.security.core.userdetails.User(user.username,
             user.password,
             ArrayList())
     }
   /* fun saveUser(reqLogin: ReqLogin):User{
        val newUser=User()
        newUser.email=user.email
        newUser.password=user.password
        return user.save()

    }*/
 }

