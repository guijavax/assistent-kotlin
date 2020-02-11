package com.api.assistent.assistentkotlin.service

import com.api.assistent.assistentkotlin.entities.StateEntitie
import com.api.assistent.assistentkotlin.repositorie.StatesRepositorie
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


interface StatesService {

    fun findAll() : List<StateEntitie>
}