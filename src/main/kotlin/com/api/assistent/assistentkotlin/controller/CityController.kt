package com.api.assistent.assistentkotlin.controller

import com.api.assistent.assistentkotlin.exception.BusinessException
import com.api.assistent.assistentkotlin.service.CityService
import com.api.assistent.assistentkotlin.utils.Routes.Companion.ROOT
import com.api.assistent.assistentkotlin.utils.Utils.Companion.mountRespoonseEntityException
import com.api.assistent.assistentkotlin.utils.Utils.Companion.responseEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping(path = ["$ROOT/city"])
@RestController
class CityController {

    @Autowired
    lateinit var service : CityService

    @GetMapping(path=["/findAll"])
    fun findAll() : ResponseEntity<Any>? {
       return try {
            responseEntity(service.findAll())
        } catch (e : BusinessException) {
           mountRespoonseEntityException(HttpStatus.INTERNAL_SERVER_ERROR, e.message.toString())
        }
    }

    @GetMapping(path=["/findByState/{id}"])
    fun findCityByState(@PathVariable("id") id : Long) : ResponseEntity<Any>? {
       return try {
            responseEntity(service.findCityByState(id))
        } catch(e : BusinessException) {
           mountRespoonseEntityException(HttpStatus.INTERNAL_SERVER_ERROR, e.message.toString())
        }
        return  null
    }
}