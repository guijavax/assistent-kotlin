package com.api.assistent.assistentkotlin.utils

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

class JwtAuthentication : GenericFilterBean() {
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val  httpServletRequest : HttpServletRequest = request as HttpServletRequest
        val serviceRequest = httpServletRequest.requestURL.toString()

        if (serviceRequest.contains("/customer/newUser") || serviceRequest.contains("/customer/resetByEmail") ||
                serviceRequest.contains("/customer/reset/email") || serviceRequest.contains("/offers/offline")) {
            chain!!.doFilter(request, response);
            return
        }

        SecurityContextHolder.getContext().authentication = TokenServiceAuthentication.getAuthentication(httpServletRequest)
        chain!!.doFilter(request, response)
    }

}