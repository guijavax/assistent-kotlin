package com.api.assistent.assistentkotlin.controller

import com.api.assistent.assistentkotlin.entities.ClientEntitie
import com.api.assistent.assistentkotlin.exception.BusinessException
import com.api.assistent.assistentkotlin.service.ClientService
import com.api.assistent.assistentkotlin.utils.Routes
import com.api.assistent.assistentkotlin.utils.Util
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import javax.servlet.http.HttpServletResponse
import com.api.assistent.assistentkotlin.utils.Utils.Companion.mountRespoonseEntityException
import com.api.assistent.assistentkotlin.utils.Utils.Companion.responseEntity

@RestController
@RequestMapping(path=[Routes.ROOT+"/client"])
class ClientController {

    @Autowired
     lateinit var clientService : ClientService

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
            mountRespoonseEntityException(HttpStatus.INTERNAL_SERVER_ERROR, e.message.toString())
        }
    }

    @GetMapping(path=["/findAll"])
    fun findAll() : ResponseEntity<Any>? {
        return try {
             responseEntity(clientService.findAll())
        } catch (e : BusinessException) {
            mountRespoonseEntityException(HttpStatus.INTERNAL_SERVER_ERROR, e.message.toString())
        }
    }

    @GetMapping(path=["/findByCpf/{cpf}"])
    fun findByCpf(@PathVariable("cpf") cpf : Long) : ResponseEntity<Any> {
        return try {
           responseEntity(clientService.findClientByCpf(cpf) as ClientEntitie)
        } catch (e : Exception) {
            mountRespoonseEntityException(HttpStatus.INTERNAL_SERVER_ERROR, e.message.toString())
        }
    }

    @Suppress("UNCHECKED_CAST")
    @GetMapping(path=["/findByName/{name}"])
    fun findClientByName(@PathVariable("name") name : String) : ResponseEntity<Any> {
        return try {
           responseEntity(clientService.findClientByName(name))
        } catch (e : BusinessException) {
            mountRespoonseEntityException(HttpStatus.INTERNAL_SERVER_ERROR, e.message.toString())
        }
    }
}