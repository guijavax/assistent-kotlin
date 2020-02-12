package com.api.assistent.assistentkotlin.service

import com.api.assistent.assistentkotlin.entities.CityEntitie

interface CityService {

    fun findAll() : List<CityEntitie>

    fun findCityByState(idState : Long) : List<CityEntitie>
}