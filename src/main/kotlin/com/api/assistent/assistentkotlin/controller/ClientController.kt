package com.api.assistent.assistentkotlin.controller

import com.api.assistent.assistentkotlin.entities.ClientEntitie
import com.api.assistent.assistentkotlin.repositorie.ClientRepositorie
import com.api.assistent.assistentkotlin.utils.Routes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path=[Routes.ROOT+"/client"])
class ClientController {

    @Autowired
    lateinit var repositorie : ClientRepositorie

    @PostMapping(path= ["/insertClient"])
    fun insert(@RequestBody client : ClientEntitie): ResponseEntity<ClientEntitie> {
        var clientEntitie: ClientEntitie = repositorie.save(client)
        return ResponseEntity.ok(clientEntitie)
    }

    @GetMapping(path=["/findAll"])
    fun findAll() : List<ClientEntitie>? {
        return repositorie.findAll()
    }
}