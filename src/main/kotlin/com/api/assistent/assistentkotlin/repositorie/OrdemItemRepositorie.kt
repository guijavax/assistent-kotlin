package com.api.assistent.assistentkotlin.repositorie

import com.api.assistent.assistentkotlin.entities.OrdemItens
import org.springframework.data.jpa.repository.JpaRepository

interface OrdemItemRepositorie : JpaRepository<OrdemItens, Long>