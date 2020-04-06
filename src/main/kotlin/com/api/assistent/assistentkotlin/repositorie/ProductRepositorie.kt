package com.api.assistent.assistentkotlin.repositorie

import com.api.assistent.assistentkotlin.entities.ProductEntitie
import com.api.assistent.assistentkotlin.entities.ServiceEntitie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface  ProductRepositorie : JpaRepository<ProductEntitie, Long> {

    @Query(value = "SELECT * FROM products WHERE product_name like :name%", nativeQuery = true)
    fun findByName(@Param("name") name: String): List<ProductEntitie>
}