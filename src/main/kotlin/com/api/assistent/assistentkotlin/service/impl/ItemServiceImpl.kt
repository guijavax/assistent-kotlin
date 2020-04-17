package com.api.assistent.assistentkotlin.service.impl

import com.api.assistent.assistentkotlin.entities.ItemEntitie
import com.api.assistent.assistentkotlin.exception.BusinessException
import com.api.assistent.assistentkotlin.repositorie.ItemRepositorie
import com.api.assistent.assistentkotlin.service.ItemService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

@Service
class ItemServiceImpl : ItemService {

    @Autowired
    lateinit var repository: ItemRepositorie

    override fun insertItem(itemEntitie: ItemEntitie) {
        try {
            repository.save(itemEntitie)
        } catch (e : Exception) {
            throw BusinessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Problema ao inserir Item")
        }
    }

}