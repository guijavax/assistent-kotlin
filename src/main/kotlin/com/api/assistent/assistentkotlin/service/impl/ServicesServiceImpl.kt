package com.api.assistent.assistentkotlin.service.impl

import com.api.assistent.assistentkotlin.entities.ServiceEntitie
import com.api.assistent.assistentkotlin.repositorie.ServiceRepositorie
import com.api.assistent.assistentkotlin.service.ServicesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service("serviceImpl")
class ServicesServiceImpl : ServicesService {

    @Autowired
    private lateinit var repositorie : ServiceRepositorie

   override fun insert (service : ServiceEntitie) : ServiceEntitie {
       return repositorie.save(service)
   }

    override fun insertGroup(t: List<ServiceEntitie>): List<ServiceEntitie> {
        TODO("Not yet implemented")
    }

    override fun findById(id: Long): ServiceEntitie? {
        return repositorie.getOne(id)
    }

    override fun findAll(): List<ServiceEntitie> {
        TODO("Not yet implemented")
    }


}