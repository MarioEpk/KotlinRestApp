package com.morosystems.kotlinrest

import com.morosystems.kotlinrest.service.UserService
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
open class KotlinSecurityConfiguration(private val userService: UserService) : WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(security: HttpSecurity) {
        security.authorizeRequests()
            .antMatchers("/users").permitAll()
            .antMatchers("/secure/**").authenticated()
            .and()
            .httpBasic()
            .and()
            .csrf().disable()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(authenticationProvider())
    }

    private fun passwordEncoder(): PasswordEncoder? =
        BCryptPasswordEncoder()

    private fun authenticationProvider(): DaoAuthenticationProvider? {
        val daoAuthenticationProvider = DaoAuthenticationProvider()
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder())
        daoAuthenticationProvider.setUserDetailsService(userService)

        return daoAuthenticationProvider
    }
}