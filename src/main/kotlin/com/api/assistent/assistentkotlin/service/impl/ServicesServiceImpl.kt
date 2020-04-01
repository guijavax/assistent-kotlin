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


}