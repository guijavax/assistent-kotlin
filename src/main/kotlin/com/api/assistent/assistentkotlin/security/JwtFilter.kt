package com.api.assistent.assistentkotlin.security

import com.api.assistent.assistentkotlin.entities.UserEntitie
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
        this.authenticationManager = authenticationManager
    }

    @Throws(AuthenticationException :: class, IOException :: class, ServletException :: class)
    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        val userInfo: UserEntitie?
        if (request!!.method == "OPTIONS") {
            userInfo = UserEntitie()
            userInfo.userName = "teste@teste"
            userInfo.password = "1"

        } else {
            userInfo = setUserInfo(request)
        }
        return this.authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(userInfo!!.userName,
                        userInfo.password,
                        Collections.emptyList())
        )
    }

    private fun setUserInfo(request: HttpServletRequest) =
            ObjectMapper().readValue(request.inputStream, UserEntitie::class.java)

    override fun successfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse?, chain: FilterChain?, authResult: Authentication?) {
        TokenServiceAuthentication.addAuthentication(response!!, authResult!!.name)
        val userInfo = setUserInfo(request!!)
    }
}