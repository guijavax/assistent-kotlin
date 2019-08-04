package com.api.assistent.assistentkotlin.repositorie

import com.api.assistent.assistentkotlin.entities.ClientEntitie
import org.springframework.data.jpa.repository.JpaRepository

interface ClientRepositorie : JpaRepository<ClientEntitie, Long> {}