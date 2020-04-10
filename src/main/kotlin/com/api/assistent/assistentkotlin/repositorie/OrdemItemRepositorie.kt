package com.api.assistent.assistentkotlin.repositorie

import com.api.assistent.assistentkotlin.entities.TypeOrderItemEntitie
import org.springframework.data.jpa.repository.JpaRepository

interface OrdemItemRepositorie : JpaRepository<TypeOrderItemEntitie, Long>