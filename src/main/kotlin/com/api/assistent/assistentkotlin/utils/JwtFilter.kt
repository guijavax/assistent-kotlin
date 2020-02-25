package com.api.assistent.assistentkotlin.utils

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import java.io.IOException
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtFilter(url : String, authenticationManager: AuthenticationManager) : AbstractAuthenticationProcessingFilter(AntPathRequestMatcher(url)){

    init {
        this.setAuthenticationManager(authenticationManager)
    }

    @Throws(AuthenticationException :: class, IOException :: class, ServletException :: class)
    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        val userInfo: UserInfo?
        if (request!!.method == "OPTIONS") {
            userInfo = UserInfo()
            userInfo.email = "teste@teste"
            userInfo.password = "1"

        } else {
            userInfo = ObjectMapper().readValue(request.inputStream, UserInfo::class.java)
        }
        return this.authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(userInfo!!.email,
                        userInfo.password,
                        Collections.emptyList())
        )
    }

    override fun successfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse?, chain: FilterChain?, authResult: Authentication?) {
        TokenServiceAuthentication.addAuthentication(response!!,authResult!!.name)
    }
}