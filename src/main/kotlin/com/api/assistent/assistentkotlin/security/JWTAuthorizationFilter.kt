package com.api.assistent.assistentkotlin.security

import com.api.assistent.assistentkotlin.service.LoginService
import com.api.assistent.assistentkotlin.utils.AUTHORIZATION
import com.api.assistent.assistentkotlin.utils.AUTHORIZATION_VALUE
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthorizationFilter : BasicAuthenticationFilter{

    private var jwtUtil : JWTUtil

    @Autowired
    private lateinit var userDetails : LoginService

    constructor(authenticationManager: AuthenticationManager, jwtUtil: JWTUtil) : super(authenticationManager){
        this.jwtUtil = jwtUtil
    }

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val authorizationHeader = request.getHeader(AUTHORIZATION)

        if(authorizationHeader.startsWith(AUTHORIZATION_VALUE)) {
            SecurityContextHolder.getContext().authentication = getAuthenticationHeader(authorizationHeader)
        }
        chain.doFilter(request, response)
    }

    private fun getAuthenticationHeader(authorization : String) : UsernamePasswordAuthenticationToken {
        val token = authorization!!.substring(7)?:""

        if (jwtUtil.isTokenValid(token)) {
            val user = userDetails.loadUserByUsername(jwtUtil.getUserName(token))
            return UsernamePasswordAuthenticationToken(user, null, user.authorities)
        }
        throw UsernameNotFoundException("Auth Invalid")
    }

}