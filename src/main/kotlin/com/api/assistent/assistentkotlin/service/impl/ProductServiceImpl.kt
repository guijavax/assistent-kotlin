package com.api.assistent.assistentkotlin.service.impl

import com.api.assistent.assistentkotlin.entities.ProductEntitie
import com.api.assistent.assistentkotlin.exception.BusinessException
import com.api.assistent.assistentkotlin.repositorie.ProductRepositorie
import com.api.assistent.assistentkotlin.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus.*
import org.springframework.stereotype.Service

@Service("productService")
class ProductServiceImpl : ProductService<ProductEntitie> {

    @Autowired
    lateinit var repositorie : ProductRepositorie

    override fun insert(productEntitie : ProductEntitie) : ProductEntitie? {
        return try {
            repositorie.save(productEntitie)
        } catch (e : Exception) {
            throw BusinessException(INTERNAL_SERVER_ERROR.value(), "Problema ao salvar produto")
        }
    }

    override fun insertGroup(products: List<ProductEntitie>): List<ProductEntitie> {
        return try {
            repositorie.saveAll(products)
        } catch (e : Exception) {
            throw BusinessException(INTERNAL_SERVER_ERROR.value(), "Problema ao salvar produtos")
        }
    }

    override fun findById(id: Long): ProductEntitie? {
        return try {
            repositorie.findById(id).get()
        } catch (e : NoSuchElementException) {
            throw BusinessException(NO_CONTENT.value(), e.message.toString())
        } catch (e : Exception) {
            throw BusinessException(INTERNAL_SERVER_ERROR.value(), e.message.toString())
        }
    }

    override fun findAll(): List<ProductEntitie> {
        return try {
            repositorie.findAll()
        } catch (e : Exception) {
            throw BusinessException(INTERNAL_SERVER_ERROR.value(), e.message.toString())
        }
    }

    override fun findByName(name: String): List<ProductEntitie> {
        return try {
            repositorie.findByName(name)
        } catch (e : Exception) {
            throw BusinessException(INTERNAL_SERVER_ERROR.value(), e.message.toString())
        }
    }

    override fun findByType(type: Long) : List<ProductEntitie> {
        return try {
            repositorie.findByType(type)
        } catch (e : Exception) {
            throw BusinessException(INTERNAL_SERVER_ERROR.value(), e.message.toString())
        }
    }
}