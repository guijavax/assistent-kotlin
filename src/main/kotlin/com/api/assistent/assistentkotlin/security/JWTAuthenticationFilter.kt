package com.api.assistent.assistentkotlin.security

import com.api.assistent.assistentkotlin.models.Credentials
import com.api.assistent.assistentkotlin.models.UserDetailsImpl
import com.api.assistent.assistentkotlin.utils.AUTHORIZATION
import com.api.assistent.assistentkotlin.utils.AUTHORIZATION_VALUE
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationFilter : UsernamePasswordAuthenticationFilter {

    private var jwtUtil : JWTUtil

    constructor(authenticationManager : AuthenticationManager, jwtUtil : JWTUtil) : super() {
        this.jwtUtil = jwtUtil
        this.authenticationManager = authenticationManager
    }

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication? {
        try {
            ObjectMapper().readValue(request!!.inputStream, Credentials::class.java).let {
                return  authenticationManager.authenticate(UsernamePasswordAuthenticationToken(it.username, it.password))
            }
        } catch (e : Exception) {
            throw UsernameNotFoundException(e.message)
        }
    }

    override fun successfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse?, chain: FilterChain?, authResult: Authentication?) {
        val token = jwtUtil.generateToken((authResult!!.principal as UserDetailsImpl).username)
        response!!.addHeader(AUTHORIZATION, "$AUTHORIZATION_VALUE $token")
    }

}