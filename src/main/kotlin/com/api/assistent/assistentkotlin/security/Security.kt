package com.api.assistent.assistentkotlin.security

import com.api.assistent.assistentkotlin.repositorie.UserRepositorie
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.header.writers.StaticHeadersWriter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import java.security.AuthProvider
import javax.servlet.http.HttpServletResponse

@Configuration
@EnableWebSecurity
class Security : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var userRepositorie : UserRepositorie

    override fun configure(http: HttpSecurity?) {
        http!!.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST,"/signup").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(JWTLoginFIlter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter::class.java)
                .addFilterBefore(JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter::class.java)
    }

    private fun mountExceptionHandler(http: HttpSecurity?) {
            http!!.exceptionHandling().authenticationEntryPoint (AuthenticationEntryPoint { request, response, e ->
                mountResponse(response, String.format("{\"message\": \"%s\"}", e.message))
            })
                    .and()
                    .anonymous()
                    .and()
                    .servletApi()
                    .and()
                    .headers()
                    .addHeaderWriter(StaticHeadersWriter("Access-Control-Allow-Origin", "*"))
                    .addHeaderWriter(StaticHeadersWriter("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT"))
                    .addHeaderWriter(StaticHeadersWriter("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With, X-Custom-Header"))
                    .addHeaderWriter(StaticHeadersWriter("Access-Control-Max-Age", "3600"))

    }

    private fun mountResponse(response : HttpServletResponse, json : String)  {
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        response.contentType = "application/json"
        response.characterEncoding = "UTF-8"
        response.writer.write(json)
    }

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.authenticationProvider(Authentication(userRepositorie))
    }

    @Bean
    fun corsFilter() : FilterRegistrationBean<CorsFilter> {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()

        config.allowCredentials = true
        config.addAllowedOrigin("*")
        config.addAllowedMethod("*")
        config.addAllowedHeader("*")
        source.registerCorsConfiguration("/**" , config)

        val filterRegistrationBean = FilterRegistrationBean(CorsFilter(source))
        filterRegistrationBean.order = 0
        return  filterRegistrationBean
    }
}