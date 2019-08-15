package com.api.assistent.assistentkotlin.controller

import com.api.assistent.assistentkotlin.entities.ClientEntitie
import com.api.assistent.assistentkotlin.repositorie.ClientRepositorie
import com.api.assistent.assistentkotlin.service.ClientService
import com.api.assistent.assistentkotlin.utils.Routes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.sql.SQLException
import org.apache.logging.log4j.kotlin.logger
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import java.util.*
import javax.servlet.ServletContext
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping(path=[Routes.ROOT+"/client"])
class ClientController {

    @Autowired
     lateinit var clientService : ClientService
     val LOGGER = logger()

    @PostMapping(path= ["/insertClient"])
    fun insert(@RequestBody client : ClientEntitie, response : HttpServletResponse): ResponseEntity<ClientEntitie>? {
        try {
           var clientEntitie  = clientService.insertClient(client)
            if (clientEntitie != null) {
               var uri : URI = ServletUriComponentsBuilder.fromCurrentRequestUri()
                       .path("/findByCpf/{cpf}")
                       .buildAndExpand(clientEntitie.cpf)
                       .toUri()
                response.setHeader("Location", uri.toASCIIString())
                return ResponseEntity.created(uri).build()
            }
        } catch(e : Exception) {
            LOGGER.error(e.message!!)
        }
        return null
    }

    @GetMapping(path=["/findAll"])
    fun findAll() : ResponseEntity<List<ClientEntitie>>? {
        try {
            return ResponseEntity.ok(clientService.findAll())
        } catch (e : Exception) {
            LOGGER.error(e.message!!)
        }
        return ResponseEntity.noContent().build()
    }

    @GetMapping(path=["/findByCpf/{cpf}"])
    fun findByCpf(@PathVariable("cpf") cpf : Long) : ResponseEntity<ClientEntitie>? {
        try {
           var clientEntitie = clientService.findClientByCpf(cpf)
           return if(clientEntitie != null) ResponseEntity.ok(clientEntitie) else ResponseEntity.noContent().build()
        } catch (e : Exception) {
            LOGGER.error(e.message!!)
        }
        return null
    }

    @GetMapping(path=["/findByName/{name}"])
    fun findClientByName(@PathVariable("name") name : String) : ResponseEntity<List<ClientEntitie>>{
        var clients : List<ClientEntitie> = clientService.findClientByName(name)
        return if (clients.isEmpty()) ResponseEntity.noContent().build() else ResponseEntity.ok(clients)
    }
}