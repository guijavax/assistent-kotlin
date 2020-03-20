package com.api.assistent.assistentkotlin.security

import com.api.assistent.assistentkotlin.models.Credentials
import com.api.assistent.assistentkotlin.utils.JWTUtil
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JWTAuthenticationFilter: UsernamePasswordAuthenticationFilter {

    private var jwtUtil: JWTUtil

    constructor(authenticationManager: AuthenticationManager, jwtUtil: JWTUtil) : super() {
        this.authenticationManager = authenticationManager
        this.jwtUtil = jwtUtil
    }

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse?): Authentication? {
        try {
            val (username, password) = ObjectMapper().readValue(request!!.inputStream, Credentials::class.java)
            return authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, password))
        } catch (e : Exception) {
            throw UsernameNotFoundException(e.message)
        }
    }

    override fun successfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse?, chain: FilterChain?, authResult: Authentication?) {
        val token = this.jwtUtil!!.generateToken((authResult as  UserDetaisServiceImpl).username)
        response!!.addHeader("Authorization", "Bearer $token")
    }
}