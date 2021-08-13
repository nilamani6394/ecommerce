package com.ecommerce.ecommerce.config

import com.ecommerce.ecommerce.auth.Http401UnAuthorizedEntryPoint
import com.ecommerce.ecommerce.filter.JwtRequestFilter
import com.ecommerce.ecommerce.service.MyUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    @Autowired
    private lateinit var http401UnAuthorizedEntryPoint: Http401UnAuthorizedEntryPoint
    @Autowired
    private lateinit var userDetailsService: MyUserDetailsService
    @Autowired
    private lateinit var jwtRequestFilter:JwtRequestFilter




    @Throws(Exception::class)
    override fun configure(httpSecurity: HttpSecurity){
        httpSecurity.csrf()
            .disable()
            .authorizeRequests()
            .antMatchers("/signup")
            .permitAll()
            .anyRequest()
             .authenticated()
//            .and()
//            .exceptionHandling()
            //.authenticationEntryPoint(http401UnAuthorizedEntryPoint)
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and()
//            .addFilterBefore(jwtRequestFilter,UsernamePasswordAuthenticationFilter::class.java)


    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(userDetailsService)
    }
    @Bean
    override fun authenticationManagerBean():AuthenticationManager{
        return super.authenticationManagerBean()
    }
    @Bean
    fun bcryptPasswordEncoder(): PasswordEncoder? {
        return NoOpPasswordEncoder.getInstance()
    }

}

