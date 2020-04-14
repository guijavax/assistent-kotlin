package com.api.assistent.assistentkotlin.controller

import com.api.assistent.assistentkotlin.entities.CartEntitie
import com.api.assistent.assistentkotlin.exception.BusinessException
import com.api.assistent.assistentkotlin.service.CartService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.api.assistent.assistentkotlin.utils.Routes.Companion.ROOT
import com.api.assistent.assistentkotlin.utils.Utils.Companion.mountRespoonseEntityException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@RestController
@RequestMapping("$ROOT/cart")
class CartController {

    @Autowired
    lateinit var service : CartService

    @PostMapping("/createCart")
    fun createCart(@RequestBody cart : CartEntitie) : ResponseEntity<Any>{
        return try {
            val cart = service.createCart(cart)
            ResponseEntity.ok(cart?:cart)
        } catch (e : BusinessException) {
            mountRespoonseEntityException(HttpStatus.INTERNAL_SERVER_ERROR, e.message.toString())
        }
    }
}