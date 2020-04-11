package com.api.assistent.assistentkotlin.repositorie

import com.api.assistent.assistentkotlin.entities.OrderEntitie
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepositorie : JpaRepository<OrderEntitie, Long>