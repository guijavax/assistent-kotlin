package com.api.assistent.assistentkotlin.controller

import com.api.assistent.assistentkotlin.entities.ProductEntitie
import com.api.assistent.assistentkotlin.exception.BusinessException
import com.api.assistent.assistentkotlin.generics.GenericItemTypeService
import com.api.assistent.assistentkotlin.utils.Routes.Companion.ROOT
import org.apache.logging.log4j.kotlin.logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.NotNull

@RestController
@RequestMapping(value = ["$ROOT/product"])
class ProductController {

    @Autowired
    @Qualifier("productService")
    lateinit var productService : GenericItemTypeService<ProductEntitie>

    val LOGGER = logger()


    @PostMapping("/insert")
    fun insert(@RequestBody @NotNull productEntitie : ProductEntitie) : ResponseEntity<Any> {
        return try {
            val product = productService.insert(productEntitie)
             ResponseEntity.ok().body(product?:product)
        } catch (e : BusinessException) {
            LOGGER.error(e.message.toString())
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message.toString())
        }
    }
}

