package com.ecommerce.ecommerce.util

import com.ecommerce.ecommerce.JWT_TOKEN_VALIDITY
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.io.Serializable
import java.util.*


@Component
class JwtTokenUtil : Serializable {
    @Value("\${jwt.secret}")
    lateinit var secret: String
    /**Retrieve username from token
    fun getUserNameFromToken(token: String):String{
        return getClaimsFromToken(token,Claims::getSubject)
    }*/
    /**Generate Token For User*/
    fun generateToken(userDetails: UserDetails): String {
        val claims: Map<String, Any> = HashMap()
        return doGenerateToken(claims, userDetails.username)
    }

     fun doGenerateToken(claims: Map<String, Any>, email: String): String {
        return Jwts.builder().setClaims(claims).setSubject(email).setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis()+ JWT_TOKEN_VALIDITY*1000))
            .signWith(SignatureAlgorithm.HS512,secret).compact()
    }

}

fun generateToken(userDetails: UserDetails): String? {
    val claims: Map<String, Any> = HashMap()
    return doGenerateToken(claims, userDetails.username)
}




