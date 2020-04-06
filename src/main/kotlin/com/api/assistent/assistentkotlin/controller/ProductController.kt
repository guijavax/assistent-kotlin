package com.api.assistent.assistentkotlin.controller

import com.api.assistent.assistentkotlin.entities.ProductEntitie
import com.api.assistent.assistentkotlin.exception.BusinessException
import com.api.assistent.assistentkotlin.generics.GenericItemTypeService
import com.api.assistent.assistentkotlin.service.ProductService
import com.api.assistent.assistentkotlin.utils.Routes.Companion.ROOT
import com.api.assistent.assistentkotlin.utils.Utils.Companion.mountHttpStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.NotNull
import com.api.assistent.assistentkotlin.utils.Utils.Companion.mountRespoonseEntityException
import com.api.assistent.assistentkotlin.utils.Utils.Companion.responseEntity
@RestController
@RequestMapping(value = ["$ROOT/product"])
class ProductController {

    @Autowired
    @Qualifier(value = "productService")
    lateinit var productService : ProductService<ProductEntitie>

    @PostMapping("/insert")
    fun insert(@RequestBody @NotNull productEntitie : ProductEntitie) : ResponseEntity<Any> {
        return try {
            val product = productService.insert(productEntitie)
             ResponseEntity.ok().body(product?:product)
        } catch (e : BusinessException) {
            mountRespoonseEntityException(HttpStatus.INTERNAL_SERVER_ERROR, e.message.toString())
        }
    }

    @GetMapping("/findById/{id}")
    fun findById(@PathVariable(value="id") id : Long) : ResponseEntity<Any>{
        return try {
            responseEntity(productService.findById(id) as  ProductEntitie)
        } catch (e : BusinessException) {
            mountRespoonseEntityException(mountHttpStatus(e), e.message.toString())
        }
    }

    @PostMapping("/insertGroup")
    fun insertGroupProducts(@RequestBody products : List<ProductEntitie>) : ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(productService.insertGroup(products))
        } catch(e : BusinessException) {
            mountRespoonseEntityException(HttpStatus.INTERNAL_SERVER_ERROR, e.message.toString())
        }
    }

    @GetMapping("/findAll")
    fun findAll() : ResponseEntity<Any> {
        return try {
            responseEntity(productService.findAll())
        } catch (e : BusinessException) {
            mountRespoonseEntityException(HttpStatus.INTERNAL_SERVER_ERROR, e.message.toString())
        }
    }

    @GetMapping("/findByName/{name}")
    fun findByName(@PathVariable("name") name : String) : ResponseEntity<Any> {
        return try {
            responseEntity(productService.findByName(name))
        } catch (e : BusinessException) {
            mountRespoonseEntityException(HttpStatus.INTERNAL_SERVER_ERROR, e.message.toString())
        }
    }
}

