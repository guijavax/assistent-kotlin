package com.api.assistent.assistentkotlin.service.impl

import com.api.assistent.assistentkotlin.entities.UserEntitie
import com.api.assistent.assistentkotlin.models.UserDetailsImpl
import com.api.assistent.assistentkotlin.repositorie.UserRepositorie
import com.api.assistent.assistentkotlin.service.LoginService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class LoginServiceImpl : LoginService {

    @Autowired
    private lateinit var userRepositorie : UserRepositorie

    override fun loadUserByUsername(username: String?): UserDetails {
       val result = userRepositorie.findByUsername(username!!)
        val user = UserEntitie()
        user.username = result!!.get("username").toString()
        user.password = result!!.get("password").toString()
       return UserDetailsImpl(user)

    }

}