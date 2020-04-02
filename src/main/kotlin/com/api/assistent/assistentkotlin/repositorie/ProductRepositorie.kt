package com.api.assistent.assistentkotlin.repositorie

import com.api.assistent.assistentkotlin.entities.ProductEntitie
import org.springframework.data.jpa.repository.JpaRepository

interface  ProductRepositorie : JpaRepository<ProductEntitie, Long>