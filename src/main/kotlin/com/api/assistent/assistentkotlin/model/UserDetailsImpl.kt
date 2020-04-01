package com.api.assistent.assistentkotlin.model

import com.api.assistent.assistentkotlin.entities.UserEntitie
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsImpl(private val user : UserEntitie) : UserDetails {

    override fun getAuthorities() = mutableListOf<GrantedAuthority>()

    override fun isEnabled() = true

    override fun getUsername()  = this.user.username

    override fun isCredentialsNonExpired() = true

    override fun getPassword() = this.user.password

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

}