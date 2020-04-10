package com.api.assistent.assistentkotlin.controller

import com.api.assistent.assistentkotlin.exception.BusinessException
import com.api.assistent.assistentkotlin.service.StatesService
import com.api.assistent.assistentkotlin.utils.Routes
import com.api.assistent.assistentkotlin.utils.Utils.Companion.mountRespoonseEntityException
import com.api.assistent.assistentkotlin.utils.Utils.Companion.responseEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = [Routes.ROOT+"/state"])
class StatesController {

    @Autowired
    lateinit var statesService : StatesService

    @GetMapping(path = ["/findAll"])
    fun findAll() : ResponseEntity<Any>? {
        return try {
           responseEntity(statesService.findAll())
        }catch (e : BusinessException) {
            mountRespoonseEntityException(HttpStatus.INTERNAL_SERVER_ERROR, e.message.toString())
        }
    }
}