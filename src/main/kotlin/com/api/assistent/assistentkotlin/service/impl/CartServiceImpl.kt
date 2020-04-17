package com.api.assistent.assistentkotlin.service.impl

import com.api.assistent.assistentkotlin.entities.CartEntitie
import com.api.assistent.assistentkotlin.entities.ItemEntitie
import com.api.assistent.assistentkotlin.exception.BusinessException
import com.api.assistent.assistentkotlin.generics.GenericItemTypeService
import com.api.assistent.assistentkotlin.repositorie.CartRepositorie
import com.api.assistent.assistentkotlin.repositorie.ItemRepositorie
import com.api.assistent.assistentkotlin.service.CartService
import com.api.assistent.assistentkotlin.service.ItemService
import com.api.assistent.assistentkotlin.utils.Utils.Companion.isNotEmptyList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.sql.SQLException
import kotlin.math.sign


@Service
class CartServiceImpl : CartService {

    @Autowired
    lateinit var repository : CartRepositorie

   private val itemService : ItemService = ItemServiceImpl()

    override fun createCart(cart : CartEntitie) : CartEntitie {
       return try {
           val newCart = repository.save(cart)
           newCart
        } catch(e : Exception) {
            throw BusinessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Problema ao inserir carrinho")
       }
    }

    @Throws(SQLException::class)
    private fun subtractAmount(item : ItemEntitie) {
         try {
             item?.productEntitie?.idProduct?.let { repository.updateAmountProduct(it) }
        } catch (e : Exception) {
             throw Exception(e.message)
         }
    }

    private fun insertItem(itens : List<ItemEntitie>) {
        try {
            itens.forEach { item ->
                itemService.insertItem(item)
                this.subtractAmount(item)
            }
        } catch (e : Exception){
            throw Exception(e)
        }
    }
}
