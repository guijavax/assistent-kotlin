package com.api.assistent.assistentkotlin.service.impl

import com.api.assistent.assistentkotlin.entities.ProductEntitie
import com.api.assistent.assistentkotlin.exception.BusinessException
import com.api.assistent.assistentkotlin.repositorie.ProductRepositorie
import com.api.assistent.assistentkotlin.service.ProductService
import org.apache.logging.log4j.kotlin.logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service("productService")
class ProductServiceImpl : ProductService {

    @Autowired
    lateinit var repositorie : ProductRepositorie

    val LOGGER = logger()

    override fun insert(productEntitie : ProductEntitie) : ProductEntitie? {
        val
            return repositorie.save(productEntitie)?: throw BusinessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Pro")
    }





}