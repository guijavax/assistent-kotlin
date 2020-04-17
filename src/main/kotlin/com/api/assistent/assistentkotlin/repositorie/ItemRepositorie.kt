package com.api.assistent.assistentkotlin.repositorie

import com.api.assistent.assistentkotlin.entities.ItemEntitie
import org.springframework.data.jpa.repository.JpaRepository

interface ItemRepositorie : JpaRepository<ItemEntitie, Long>