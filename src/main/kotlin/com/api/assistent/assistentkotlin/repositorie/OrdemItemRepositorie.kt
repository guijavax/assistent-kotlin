package com.api.assistent.assistentkotlin.repositorie

import com.api.assistent.assistentkotlin.entities.OrderItemsEntitie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrdemItemRepositorie : JpaRepository<OrderItemsEntitie, Long> {

}