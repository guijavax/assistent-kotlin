package com.api.assistent.assistentkotlin.security

import com.api.assistent.assistentkotlin.entities.UserEntitie
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTLoginFIlter(url: String, authManager: AuthenticationManager) : AbstractAuthenticationProcessingFilter(AntPathRequestMatcher(url)) {

    init {
        this.authenticationManager = authManager
    }

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        val userInfo : UserEntitie? = setUser(request)
        return authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(userInfo!!.username, userInfo.password, mutableListOf<GrantedAuthority>()))
    }

    private fun setUser(request : HttpServletRequest?) : UserEntitie {
        var userEntitie : UserEntitie? = null
        if (request!!.method == "OPTIONS") {
            userEntitie = UserEntitie()
            userEntitie.username = "opt"
            userEntitie.password = "1"
        }
        else {
            userEntitie = ObjectMapper().readValue(request.inputStream, UserEntitie::class.java)
        }
        return userEntitie!!
    }

    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse, chain: FilterChain?, authResult: Authentication) {
        TokenServiceAuthetication.addAuthentication(response, authResult.name)
    }
}