package com.api.assistent.assistentkotlin.controller

import com.api.assistent.assistentkotlin.entities.ClientEntitie
import com.api.assistent.assistentkotlin.service.ClientService
import com.api.assistent.assistentkotlin.utils.Routes
import com.api.assistent.assistentkotlin.utils.Util
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.apache.logging.log4j.kotlin.logger
import org.springframework.http.HttpStatus
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import javax.servlet.http.HttpServletResponse


@RestController
@RequestMapping(path=[Routes.ROOT+"/client"])
class ClientController {

    @Autowired
     lateinit var clientService : ClientService
     val LOGGER = logger()

    val util : Util = Util()

    @PostMapping(path= ["/insertClient"])
    fun insert(@RequestBody client : ClientEntitie, response : HttpServletResponse): ResponseEntity<Any>? {
        return try {
           clientService.insertClient(client).let {
              val uri: URI = ServletUriComponentsBuilder.fromCurrentRequestUri()
                      .path("/findByCpf/{cpf}")
                      .buildAndExpand(it.cpf)
                      .toUri()
              response.setHeader("Location", uri.toASCIIString())
              ResponseEntity.created(uri).body(it)
          }
        } catch(e : Exception) {
            LOGGER.error(e.message!!)
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message)
        }
    }

    @GetMapping(path=["/findAll"])
    fun findAll() : ResponseEntity<Any>? {
        return try {
             ResponseEntity.ok(clientService.findAll())
        } catch (e : Exception) {
            LOGGER.error(e.message!!)
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message)
        }
    }

    @GetMapping(path=["/findByCpf/{cpf}"])
    fun findByCpf(@PathVariable("cpf") cpf : Long) : ResponseEntity<Any>? {
        return try {
            val clientEntitie : ClientEntitie? = clientService.findClientByCpf(cpf)
            if (clientEntitie != null) ResponseEntity.ok(clientEntitie) else ResponseEntity.noContent().build()
        } catch (e : Exception) {
            LOGGER.error(e.message!!)
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message)
        }
    }

    @Suppress("UNCHECKED_CAST")
    @GetMapping(path=["/findByName/{name}"])
    fun findClientByName(@PathVariable("name") name : String) : ResponseEntity<List<ClientEntitie>> {
        return util.returnStatus(clientService.findClientByName(name)) as ResponseEntity<List<ClientEntitie>>
    }
}