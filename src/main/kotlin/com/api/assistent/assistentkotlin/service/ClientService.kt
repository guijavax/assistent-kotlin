package com.api.assistent.assistentkotlin.service

import com.api.assistent.assistentkotlin.entities.ClientEntitie

interface ClientService {

    fun insertClient (client : ClientEntitie) : ClientEntitie

    fun findClientByCpf(cpf : Long?) : ClientEntitie?

    fun findAll() : List<ClientEntitie>

    fun findClientByName(name : String?) : List<ClientEntitie>
}