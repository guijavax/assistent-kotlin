package com.api.assistent.assistentkotlin.utils

import io.jsonwebtoken.JwtHandlerAdapter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import javax.servlet.Filter


@Configuration
@EnableWebSecurity
class Security : WebSecurityConfigurerAdapter() {

    override fun configure(http : HttpSecurity) {
        http.csrf().disable()
        .authorizeRequests()
                .antMatchers("/assistent").permitAll()
                .antMatchers(HttpMethod.POST, "*").permitAll()
                .antMatchers(HttpMethod.GET, "*").permitAll()
                .antMatchers(HttpMethod.PUT, "*").permitAll()
                .antMatchers(HttpMethod.GET, "*").permitAll()
                .anyRequest().authenticated()
                .and()
        // filtra requisições de login
                .addFilterBefore(com.api.assistent.assistentkotlin.utils.JwtFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter::class.java)
                .addFilterBefore(JwtAuthentication(), UsernamePasswordAuthenticationFilter::class.java)
    }

    @Bean
    fun corsFilter(): FilterRegistrationBean<*>? {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()
        config.allowCredentials = true
        config.addAllowedOrigin("*")
        config.addAllowedHeader("*")
        config.addAllowedMethod("*")
        source.registerCorsConfiguration("/**", config)
        val bean: FilterRegistrationBean<*> = FilterRegistrationBean<Filter>(CorsFilter(source))
        bean.order = 0
        return bean
    }
}