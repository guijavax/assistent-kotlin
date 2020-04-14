package com.api.assistent.assistentkotlin.service.impl

import com.api.assistent.assistentkotlin.entities.CartEntitie
import com.api.assistent.assistentkotlin.exception.BusinessException
import com.api.assistent.assistentkotlin.generics.GenericItemTypeService
import com.api.assistent.assistentkotlin.repositorie.CartRepositorie
import com.api.assistent.assistentkotlin.service.CartService
import com.api.assistent.assistentkotlin.utils.Utils.Companion.isNotEmptyList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service


@Service
class CartServiceImpl : CartService {

    @Autowired
    lateinit var repository : CartRepositorie

    override fun createCart(cart : CartEntitie) : CartEntitie {
       return try {
           repository.save(cart)
        } catch(e : Exception) {
            throw BusinessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Problema ao inserir carrinho")
       }
    }
}
