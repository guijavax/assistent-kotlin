package com.api.assistent.assistentkotlin.service.impl

import com.api.assistent.assistentkotlin.entities.StateEntitie
import com.api.assistent.assistentkotlin.repositorie.StatesRepositorie
import com.api.assistent.assistentkotlin.service.StatesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.sql.SQLException

@Service
class StatesServiceImpl : StatesService {

    @Autowired
    lateinit var repositorie : StatesRepositorie

    @Throws(SQLException :: class)
    override fun findAll(): List<StateEntitie> {
       repositorie.findAll().let{
           return it
       }
    }

}