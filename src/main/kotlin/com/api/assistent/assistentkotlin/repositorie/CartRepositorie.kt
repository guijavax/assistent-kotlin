package com.api.assistent.assistentkotlin.repositorie

import com.api.assistent.assistentkotlin.entities.CartEntitie
import com.api.assistent.assistentkotlin.utils.Querys
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CartRepositorie : JpaRepository<CartEntitie, Long> {

  @Query(value = Querys.minusAmountProduct, nativeQuery = true)
  fun updateAmountProduct(
          @Param("id") id : Long,
          @Param("cart") cart : Long,
          @Param("idProduct") idProduct : Long)
}