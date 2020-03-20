package com.api.assistent.assistentkotlin.security

import com.api.assistent.assistentkotlin.entities.UserEntitie
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails

class UserDetaisServiceImpl(private val user : UserEntitie) : UserDetails {

    override fun getAuthorities() =  mutableListOf<GrantedAuthority>()

    override fun isEnabled() = true

    override fun getUsername() = user.username

    override fun isCredentialsNonExpired() = true

    override fun getPassword() = user.password

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked(): Boolean = true

}