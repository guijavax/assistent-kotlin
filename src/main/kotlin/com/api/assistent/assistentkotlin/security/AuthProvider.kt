package com.api.assistent.assistentkotlin.security

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication

class AuthProvider() : AuthenticationProvider {



    override fun authenticate(authentication: Authentication?): Authentication {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun supports(authentication: Class<*>?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}