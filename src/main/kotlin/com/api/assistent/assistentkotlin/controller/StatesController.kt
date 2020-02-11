package com.api.assistent.assistentkotlin.controller

import com.api.assistent.assistentkotlin.entities.StateEntitie
import com.api.assistent.assistentkotlin.service.StatesService
import com.api.assistent.assistentkotlin.utils.Routes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.sql.SQLException
import javax.xml.ws.Response
import org.apache.logging.log4j.kotlin.logger

@RestController
@RequestMapping(path = [Routes.ROOT+"/state"])
class StatesController {

    @Autowired
    lateinit var statesService : StatesService

    val LOGGER = logger()

    @GetMapping(path = ["/findAll"])
    fun findAll() : ResponseEntity<List<StateEntitie>>? {
        try {
            val states = statesService.findAll().let {
                return if(it.isEmpty())ResponseEntity.noContent().build() else ResponseEntity.ok(it)
            }
        }catch (e : SQLException) {
            LOGGER.error(e.message!!)
        }
        return null
    }
}