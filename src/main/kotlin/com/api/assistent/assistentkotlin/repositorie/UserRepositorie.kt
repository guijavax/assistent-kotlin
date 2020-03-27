package com.api.assistent.assistentkotlin.repositorie

import com.api.assistent.assistentkotlin.entities.UserEntitie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface UserRepositorie : JpaRepository<UserEntitie, Long> {

    fun findByUsername(username : String?) : UserEntitie

}