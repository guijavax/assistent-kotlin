package com.api.assistent.assistentkotlin.repositorie

import com.api.assistent.assistentkotlin.entities.CityEntitie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.web.bind.annotation.PathVariable

interface CityRepositorie : JpaRepository<CityEntitie, Long> {

    @Query(value = "select * from cidades "
                +    "where estado_id = :id order by nome",
            nativeQuery = true)
    fun findCityByState(@Param("id") id : Long) : List<CityEntitie>
}