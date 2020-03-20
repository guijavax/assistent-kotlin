package com.api.assistent.assistentkotlin.security

import com.api.assistent.assistentkotlin.service.LoginService
import com.api.assistent.assistentkotlin.utils.AUTHORIZATION
import com.api.assistent.assistentkotlin.utils.AUTHORIZATION_VALUE
import com.api.assistent.assistentkotlin.utils.JWTUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse



class JWTAuthorizationFilter : BasicAuthenticationFilter {

    private var jwtUtil: JWTUtil
    private var loginService: UserDetailsService

    constructor(authenticationManager: AuthenticationManager, jwtUtil: JWTUtil, userDetailService: UserDetailsService) : super(authenticationManager) {
        this.jwtUtil = jwtUtil
        this.loginService = userDetailService
    }

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val authorizationHeader = request.getHeader(AUTHORIZATION)

        if (authorizationHeader.startsWith(AUTHORIZATION_VALUE)) {
            SecurityContextHolder.getContext().authentication = getAuthentication(authorizationHeader)
        }
        chain.doFilter(request, response)
    }

    private fun getAuthentication(header : String?) : UsernamePasswordAuthenticationToken{
        val token = header!!.substring(7)?:""
        if(jwtUtil!!.isValidToken(token)) {
            val username = jwtUtil!!.getUserName(token)
            val user = loginService!!.loadUserByUsername(username!!)
            return UsernamePasswordAuthenticationToken(user, null, user!!.authorities)
        }
        throw UsernameNotFoundException("Auth invalid")
    }

}