package com.api.assistent.assistentkotlin.repositorie

import com.api.assistent.assistentkotlin.entities.UserEntitie
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepositorie : JpaRepository<UserEntitie, Long> {
}