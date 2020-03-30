package com.api.assistent.assistentkotlin.controller

import com.api.assistent.assistentkotlin.entities.ServiceEntitie
import com.api.assistent.assistentkotlin.service.ServicesService
import org.apache.logging.log4j.kotlin.logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.Exception


@RestController
@RequestMapping(path = ["/services"])
class  ServicesController {

    @Autowired
    lateinit var  serviceServices : ServicesService
    val LOGGER = logger()

    @PostMapping(path = ["/insert"])
    fun insert(@RequestBody service : ServiceEntitie) : ResponseEntity<Any>? {
        return try {
            val newService : ServiceEntitie? = serviceServices.insert(service)
             if (newService != null) ResponseEntity.ok(newService) else ResponseEntity.noContent().build()
        } catch (e : Exception) {
            LOGGER.error(e.message!!)
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message)
        }
    }
}