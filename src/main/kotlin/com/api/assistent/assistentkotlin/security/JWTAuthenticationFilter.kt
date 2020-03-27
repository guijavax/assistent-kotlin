package com.api.assistent.assistentkotlin.security

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

class JWTAuthenticationFilter : GenericFilterBean() {

    @Throws(IOException :: class, ServletException :: class)
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {

        val httpServleRequest : HttpServletRequest = request as HttpServletRequest

        if(verifyService(httpServleRequest.requestURL.toString())) {
            chain!!.doFilter(request, response)
            return
        }
        SecurityContextHolder.getContext().authentication = TokenServiceAuthetication.getAuthentication(httpServleRequest)
    }

    private fun verifyService(service : String) : Boolean {
        return service.contains("/signup") || service.contains("/resetPassword") || service.contains("/logout")
    }

}