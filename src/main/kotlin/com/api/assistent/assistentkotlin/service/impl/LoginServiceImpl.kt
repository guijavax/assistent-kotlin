package com.api.assistent.assistentkotlin.service.impl

import com.api.assistent.assistentkotlin.model.UserDetailsImpl
import com.api.assistent.assistentkotlin.repositorie.UserRepositorie
import com.api.assistent.assistentkotlin.service.LoginService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class LoginServiceImpl : LoginService {

    @Autowired
    private lateinit var userRepositorie : UserRepositorie

    override fun loadUserByUsername(username: String?): UserDetails {
       val result = userRepositorie.findByUsername(username)

       return UserDetailsImpl(result)

    }

}