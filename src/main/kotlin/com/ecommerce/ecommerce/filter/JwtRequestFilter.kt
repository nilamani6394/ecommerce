package com.ecommerce.ecommerce.filter

import com.ecommerce.ecommerce.service.JwtUserDetailsService
import com.ecommerce.ecommerce.util.JwtTokenUtil
import io.jsonwebtoken.ExpiredJwtException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class JwtRequestFilter : OncePerRequestFilter() {
    @Autowired
    private lateinit var jwtUserDetailsService: JwtUserDetailsService
    @Autowired
    private lateinit var jwtTokenUtil: JwtTokenUtil

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val requestTokenHeader=request.getHeader("Authorization")
        var username: String=""
        var jwtToken:String=""

        if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")){
            jwtToken=requestTokenHeader.substring(7)
            try {
                username= jwtTokenUtil.getUsernameFromToken(jwtToken)
            }catch (e:IllegalArgumentException){
                println("Unable to get JWT Token")
            }catch (e:ExpiredJwtException){
                println("Jwt TOKEN Has expired")
            }
        }else{
            logger.warn("JWT Token doesn't begin with Bearer String")
        }
        //validate the token which I got from
        if (username != null && SecurityContextHolder.getContext().authentication == null){
            val userDetails:UserDetails=jwtUserDetailsService.loadUserByUsername(username)
            if (jwtTokenUtil.validateToken(jwtToken,userDetails)){
                val usernamePasswordAuthenticationToken=UsernamePasswordAuthenticationToken(
                    userDetails,null,userDetails.authorities
                )
                usernamePasswordAuthenticationToken.details=WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication=usernamePasswordAuthenticationToken
            }
        }
        filterChain.doFilter(request,response)
    }

}
       