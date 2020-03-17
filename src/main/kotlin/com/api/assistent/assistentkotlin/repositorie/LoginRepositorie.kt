package com.api.assistent.assistentkotlin.repositorie

import com.api.assistent.assistentkotlin.entities.LoginEntitie
import org.springframework.data.jpa.repository.JpaRepository

interface LoginRepositorie : JpaRepository<LoginEntitie, Long>