package com.api.assistent.assistentkotlin.repositorie

import com.api.assistent.assistentkotlin.entities.ServiceEntitie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ServiceRepositorie : JpaRepository<ServiceEntitie, Long> {}