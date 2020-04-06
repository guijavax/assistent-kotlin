package com.api.assistent.assistentkotlin.repositorie

import com.api.assistent.assistentkotlin.entities.ServiceEntitie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ServiceRepositorie : JpaRepository<ServiceEntitie, Long> {

    @Query(value = "SELECT * FROM services WHERE service_name like :name%", nativeQuery = true)
    fun findByName(@Param("name") name : String) : List<ServiceEntitie>
}