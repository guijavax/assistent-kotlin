package com.api.assistent.assistentkotlin.repositorie

import com.api.assistent.assistentkotlin.entities.CityEntitie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.web.bind.annotation.PathVariable

interface CityRepositorie : JpaRepository<CityEntitie, Long> {

    @Query(value = "select ci.* from cidades ci "
                +   "inner join estados uf on uf.id = ci.estado_id "
                +    "where uf.nome = :name",
            nativeQuery = true)
    fun findCityByState(@Param("name") name : String) : List<CityEntitie>
}