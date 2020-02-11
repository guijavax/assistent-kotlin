package com.api.assistent.assistentkotlin.repositorie

import com.api.assistent.assistentkotlin.entities.StateEntitie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StatesRepositorie : JpaRepository<StateEntitie, Long> {}