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
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import javax.servlet.Filter
import javax.servlet.http.HttpServletResponse


@Configuration
@EnableWebSecurity
class Security : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var repository : UserRepositorie

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
                .addFilterBefore(JwtFilter("/assistent/login", authenticationManager()), UsernamePasswordAuthenticationFilter::class.java)
                .addFilterBefore(JwtAuthentication(), UsernamePasswordAuthenticationFilter::class.java)

            http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint())
    }


    private fun authenticationEntryPoint() : AuthenticationEntryPoint {
        return AuthenticationEntryPoint{ request, response, e ->
            val json = String.format("{\"message\": \"%s\"}", e.message)
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            response.contentType = "application/json"
            response.characterEncoding = "UTF-8"
            response.writer.write(json)
        }
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