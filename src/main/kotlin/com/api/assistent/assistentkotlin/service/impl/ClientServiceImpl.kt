package com.api.assistent.assistentkotlin.service.impl

import com.api.assistent.assistentkotlin.entities.ClientEntitie
import com.api.assistent.assistentkotlin.repositorie.ClientRepositorie
import com.api.assistent.assistentkotlin.service.ClientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.sql.SQLException

@Service
class ClientServiceImpl : ClientService{

    @Autowired
    lateinit var repositorie : ClientRepositorie

    @Throws(SQLException :: class)
    override fun insertClient (client : ClientEntitie) : ClientEntitie {
        return repositorie.save(client)
    }

    @Throws(SQLException::class)
    override fun findClientByCpf(cpf: Long?): ClientEntitie {
       return repositorie.findClientByCpf(cpf!!)
    }

    @Throws(SQLException::class)
    override fun findAll() : List<ClientEntitie> {
        repositorie.findAll().let {
            return it
        }
    }

    @Throws(SQLException::class)
    override fun findClientByName(name : String?) : List<ClientEntitie> {
        return repositorie.findClientByName(name!!)
    }
}
