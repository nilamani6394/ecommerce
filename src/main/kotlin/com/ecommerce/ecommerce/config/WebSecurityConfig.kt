package com.ecommerce.ecommerce.config

import com.ecommerce.ecommerce.auth.JwtAuthenticationEntryPoint
import com.ecommerce.ecommerce.filter.JwtRequestFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    @Autowired
    private lateinit var jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint
    @Autowired
    private lateinit var jwtUserDetailsService: UserDetailsService
    @Autowired
    private lateinit var requestFilter:JwtRequestFilter

    @Autowired
    @Throws(Exception::class)
    fun configureGlobal(auth:AuthenticationManagerBuilder){
        auth.userDetailsService<UserDetailsService>(jwtUserDetailsService)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    @Throws(Exception::class)
    fun authenticationBeanManager(): AuthenticationManager
    {
          return authenticationManagerBean()
    }

    @Throws(Exception::class)
    override fun configure(httpSecurity: HttpSecurity){
        httpSecurity.csrf()
            .disable()
            .authorizeRequests()
            .antMatchers("/authenticate","/signup")
            .permitAll()
            .anyRequest()
             .authenticated()
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        httpSecurity.addFilterBefore(requestFilter,UsernamePasswordAuthenticationFilter::class.java)
    }

}

