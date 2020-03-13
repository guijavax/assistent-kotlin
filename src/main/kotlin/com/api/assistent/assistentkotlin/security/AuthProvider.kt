package com.api.assistent.assistentkotlin.security

import com.api.assistent.assistentkotlin.exception.BusinessException
import com.api.assistent.assistentkotlin.repositorie.UserRepositorie
import com.api.assistent.assistentkotlin.utils.Const
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import java.util.*


class AuthProvider(repository : UserRepositorie) : AuthenticationProvider {

    val repo = repository

    override fun authenticate(authentication: Authentication?): Authentication? {
        var userName = authentication!!.name
        var password = authentication.credentials.toString()

        if("opt".equals(userName)) {
            return UsernamePasswordAuthenticationToken(userName, password, Collections.emptyList())
        }
        var externalLogin = false

        if (userName.contains("- external")) {
            externalLogin = true
            userName = userName.replace("- externam", "")
        }

        val user = repo.findByUsername(userName)

        if (user == null) {
            throw BusinessException(Const.USER_NOT_FOUND, "Usuário não existente!")
        }
        //TODO finalizacao do metodo
        return null
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return authentication!!.equals(UsernamePasswordAuthenticationToken::class)
    }

}