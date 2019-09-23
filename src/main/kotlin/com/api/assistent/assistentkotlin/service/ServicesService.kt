package com.api.assistent.assistentkotlin.service

import com.api.assistent.assistentkotlin.entities.ServiceEntitie

interface ServicesService {

    /**
     * @author Guilherme
     */
    fun insert(service : ServiceEntitie) : ServiceEntitie?
}