package com.api.assistent.assistentkotlin.service

import com.api.assistent.assistentkotlin.generics.GenericItemTypeService

interface CartService {

    fun createCart(item : GenericItemTypeService<Any>)
}