package com.api.assistent.assistentkotlin.service

import com.api.assistent.assistentkotlin.entities.CartEntitie


interface CartService {

    fun createCart(cart : CartEntitie) : CartEntitie
}