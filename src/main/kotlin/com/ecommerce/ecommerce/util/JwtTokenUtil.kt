package com.ecommerce.ecommerce.util

import com.ecommerce.ecommerce.JWT_TOKEN_VALIDITY
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.io.Serializable
import java.util.*
import java.util.function.Function


@Component
class JwtTokenUtil : Serializable {
    @Value("\${jwt.secret}")
    lateinit var secret: String

    
    //for retrieveing any information from token we will need the secret key
     fun getAllClaimsFromToken(token: String): Claims {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body
    }

    //generate token for user
    fun generateToken(userDetails: UserDetails): String {
        val claims: Map<String, Any> = HashMap()
        return doGenerateToken(claims, userDetails.username)
    }

    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //   compaction of the JWT to a URL-safe string 
     fun doGenerateToken(claims: Map<String, Any>, subject: String): String {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
            .signWith(SignatureAlgorithm.HS512, secret).compact()
    }

    //validate token
    fun validateToken(token: String, userDetails: UserDetails): Boolean {
        val username = getUsernameFromToken(token)
        return username == userDetails.username && !isTokenExpired(token)
    }

    private fun isTokenExpired(token: String): Boolean {
        var expiration:Date=getExpirationDateFromToken(token)
        return expiration.before(Date())

    }

    private fun getExpirationDateFromToken(token: String): Date {
        return getClaimsFromToken(token,Claims::getExpiration)

    }

    private fun getClaimsFromToken(token: String, claimResolver:Function<Claims,Date>): Date {
        var claims:Claims=getAllClaimsFromToken(token)
        return claimResolver.apply(claims)

    }

    fun getUsernameFromToken(token: String): String {
        val claims=Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body
        return claims.subject
    }

}








