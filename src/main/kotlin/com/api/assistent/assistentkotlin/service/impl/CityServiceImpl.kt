package com.api.assistent.assistentkotlin.service.impl

import com.api.assistent.assistentkotlin.entities.CityEntitie
import com.api.assistent.assistentkotlin.repositorie.CityRepositorie
import com.api.assistent.assistentkotlin.service.CityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.sql.SQLException

@Service
class CityServiceImpl : CityService {

    @Autowired
    lateinit var repository : CityRepositorie

    @Throws(SQLException :: class)
    override fun findAll() : List<CityEntitie> {
        repository.findAll().let { return it }
    }

    @Throws(SQLException::class)
    override fun findCityByState(state: String) : List<CityEntitie> {
        repository.findCityByState(state).let {
            return it;
        }
    }
}