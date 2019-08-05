package com.api.assistent.assistentkotlin

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
class Security : WebSecurityConfigurerAdapter() {
    override fun configure(http : HttpSecurity) {
        http.authorizeRequests().anyRequest().permitAll()
                .and().csrf().disable();
    }
}