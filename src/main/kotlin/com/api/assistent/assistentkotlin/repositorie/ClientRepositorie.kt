package com.api.assistent.assistentkotlin.repositorie

import com.api.assistent.assistentkotlin.entities.ClientEntitie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ClientRepositorie : JpaRepository<ClientEntitie, Long> {

    @Query(value="select * from client where cpf= :cpf",nativeQuery = true)
    fun findClientByCpf(cpf : Long?) : ClientEntitie

    @Query(value="select * from client where name like concat (:name, '%')", nativeQuery = true)
    fun findClientByName(@Param("name")name : String?) : List<ClientEntitie>

}