package com.api.assistent.assistentkotlin.controller

import com.api.assistent.assistentkotlin.entities.CityEntitie
import com.api.assistent.assistentkotlin.service.CityService
import com.api.assistent.assistentkotlin.utils.Routes
import org.apache.logging.log4j.kotlin.logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.sql.SQLException
import com.api.assistent.assistentkotlin.utils.Routes.Companion.ROOT

@RequestMapping(path = ["$ROOT/city"])
@RestController
class CityController {

    @Autowired
    lateinit var service : CityService

    val LOGGER = logger()

    @GetMapping(path=["/findAll"])
    fun findAll() : ResponseEntity<List<CityEntitie>>? {
        try {
            service.findAll().let {
                return if(it.isEmpty()) ResponseEntity.noContent().build() else ResponseEntity.ok(it)
            }
        } catch (e : SQLException) {
            LOGGER.error(e.message!!)
        }
        return null
    }

    @GetMapping(path=["/findByState/{id}"])
    fun findCityByState(@PathVariable("id") id : Long) : ResponseEntity<List<CityEntitie>>? {
        try {
            service.findCityByState(id).let{
                return if (it.isEmpty()) ResponseEntity.noContent().build() else ResponseEntity.ok(it)
            }
        } catch(e : SQLException) {
            LOGGER.error(e.message!!)
        }
        return  null
    }
}