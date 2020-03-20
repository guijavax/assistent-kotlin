package com.api.assistent.assistentkotlin.service.impl

import com.api.assistent.assistentkotlin.entities.UserEntitie
import com.api.assistent.assistentkotlin.repositorie.UserRepositorie
import com.api.assistent.assistentkotlin.security.UserDetaisServiceImpl
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

    @Autowired
    private lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

    override fun create(userEntitie: UserEntitie): UserEntitie {
        userEntitie.password = bCryptPasswordEncoder.encode(userEntitie.password)
      return  userRepositorie.save(userEntitie)
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = userRepositorie.findByUsername(username!!) ?: throw UsernameNotFoundException(username)
        return UserDetaisServiceImpl(user)

    }

}