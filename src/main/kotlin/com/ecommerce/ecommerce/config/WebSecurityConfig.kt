package com.ecommerce.ecommerce.config

import com.ecommerce.ecommerce.auth.JwtAuthenticationEntryPoint
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    @Autowired
    private lateinit var jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint
    @Autowired
    private lateinit var jwtUseerDetailsService: UserDetailsService
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
    }

}

/*
@Throws(java.lang.Exception::class)
fun configure(httpSecurity: HttpSecurity) {
    // We don't need CSRF for this example
    httpSecurity.csrf().disable() // dont authenticate this particular request
        .authorizeRequests().antMatchers("/authenticate")
        .permitAll().anyRequest // all other requests need to be authenticated
    ().authenticated().and().exceptionHandling // make sure we use stateless session; session won't be used to
    // store user's state.
    ().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

    // Add a filter to validate the tokens with every request
    httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
}*/
