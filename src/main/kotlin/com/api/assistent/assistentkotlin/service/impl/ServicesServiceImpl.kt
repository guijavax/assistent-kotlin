package com.api.assistent.assistentkotlin.service.impl

import com.api.assistent.assistentkotlin.entities.ServiceEntitie
import com.api.assistent.assistentkotlin.exception.BusinessException
import com.api.assistent.assistentkotlin.repositorie.ServiceRepositorie
import com.api.assistent.assistentkotlin.service.ServicesService
import com.api.assistent.assistentkotlin.utils.DateOperations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service("serviceImpl")
class ServicesServiceImpl : ServicesService {

    @Autowired
    private lateinit var repositorie : ServiceRepositorie

    private val dateUtils = DateOperations

   override fun insert (service : ServiceEntitie) : ServiceEntitie? {
       return try {
           service.apply {
               beginDate = dateUtils.convertLocalDateToDate()
               experateDate =  dateUtils.plusDate(6)
           }
           repositorie.save(service)
       } catch (e : Exception) {
           throw BusinessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.message.toString())
       }

   }

    override fun insertGroup(t: List<ServiceEntitie>): List<ServiceEntitie> {
        TODO("Not yet implemented")
    }

    override fun findById(id: Long): ServiceEntitie? {
        return repositorie.getOne(id)
    }

    override fun findAll(): List<ServiceEntitie> {
        return try {
            repositorie.findAll()
        } catch (e : Exception) {
            throw BusinessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.message.toString())
        }
    }


}