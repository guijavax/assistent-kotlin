package com.api.assistent.assistentkotlin.controller

import com.api.assistent.assistentkotlin.entities.ServiceEntitie
import com.api.assistent.assistentkotlin.exception.BusinessException
import com.api.assistent.assistentkotlin.generics.GenericItemTypeService
import com.api.assistent.assistentkotlin.utils.Routes.Companion.ROOT
import com.api.assistent.assistentkotlin.utils.Utils.Companion.mountRespoonseEntityException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import com.api.assistent.assistentkotlin.utils.Utils.Companion.responseEntity


@RestController
@RequestMapping(path = ["$ROOT/services"])
class  ServicesController {

    @Autowired
    @Qualifier(value = "serviceImpl")
    lateinit var  serviceServices : GenericItemTypeService<ServiceEntitie>

    @PostMapping("/insert")
    fun insert(@RequestBody service : ServiceEntitie) : ResponseEntity<Any>? {
        return try {
            val newService : ServiceEntitie? = serviceServices.insert(service)
            ResponseEntity.ok().body(newService?:newService)
        } catch (e : Exception) {
            mountRespoonseEntityException(HttpStatus.INTERNAL_SERVER_ERROR, e.message.toString())
        }
    }

    @GetMapping("/findAll")
    fun findAll() : ResponseEntity<Any>{
        return try {
            responseEntity(serviceServices.findAll())
        } catch(e : BusinessException) {
            mountRespoonseEntityException(HttpStatus.INTERNAL_SERVER_ERROR, e.message.toString())
        }
    }
}