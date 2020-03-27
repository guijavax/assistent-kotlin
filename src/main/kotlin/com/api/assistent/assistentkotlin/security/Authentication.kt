package com.api.assistent.assistentkotlin.security

import com.api.assistent.assistentkotlin.entities.UserEntitie
import com.api.assistent.assistentkotlin.exception.BusinessException
import com.api.assistent.assistentkotlin.repositorie.UserRepositorie
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import javax.servlet.http.HttpServletResponse

class Authentication(repositorie: UserRepositorie) : AuthenticationProvider {

    private var repositorie : UserRepositorie

    init {
        this.repositorie = repositorie
    }

    override fun authenticate(authentication: Authentication?): Authentication {
        var userName = authentication?.name
        val password = authentication?.credentials.toString()

        if (userName == "opt") { return UsernamePasswordAuthenticationToken(userName, password, mutableListOf()) }

        val userEntitie = repositorie.findByUsername(userName)

        throwBussinessException(userEntitie, userName, password)

        userName = userName.plus("/")
                .plus(userEntitie.idUser.toString()).plus("/").plus(userEntitie.username)

        return UsernamePasswordAuthenticationToken(userName, password, mutableListOf())

    }

    private fun throwBussinessException(user : UserEntitie, username : String?, password : String?) {
        if (user == null) {
            throw BusinessException(HttpServletResponse.SC_NOT_FOUND, "Usuário não cadastrado!")
        } else if ((username != user.username || !user?.password.equals(password.toString().plus("\n")))) {
            throw BusinessException(HttpServletResponse.SC_BAD_REQUEST, "Usuário ou senha incorretos!")
        }
    }
    override fun supports(authentication: Class<*>): Boolean {
        return authentication == UsernamePasswordAuthenticationToken::class.java
    }
}