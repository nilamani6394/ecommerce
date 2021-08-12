package com.ecommerce.ecommerce.service

import com.ecommerce.ecommerce.Repository.UserRepository
import com.ecommerce.ecommerce.entity.User
import com.ecommerce.ecommerce.model.req.ReqLogin
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
     override fun loadUserByUsername(email: String): UserDetails {
         user=userRepository.findByUsername(email)
         return org.springframework.security.core.userdetails.User(user.email,
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

