package com.api.assistent.assistentkotlin.service

import com.api.assistent.assistentkotlin.entities.ItemEntitie

interface ItemService {
    fun insertItem(itemEntitie: ItemEntitie)
}