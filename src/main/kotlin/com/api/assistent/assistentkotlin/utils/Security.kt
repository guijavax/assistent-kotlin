package com.api.assistent.assistentkotlin.utils

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
class Security : WebSecurityConfigurerAdapter() {


    override fun configure(http : HttpSecurity) {
        http.csrf().disable()
        .authorizeRequests()
                .antMatchers(HttpMethod.POST, "*").permitAll()
                .antMatchers(HttpMethod.GET, "*").permitAll()
                .antMatchers(HttpMethod.PUT, "*").permitAll()
                .antMatchers(HttpMethod.GET, "*").permitAll()
    }



}