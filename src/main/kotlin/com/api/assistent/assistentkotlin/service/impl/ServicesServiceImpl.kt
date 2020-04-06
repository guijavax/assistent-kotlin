package com.api.assistent.assistentkotlin.service.impl

import com.api.assistent.assistentkotlin.entities.ServiceEntitie
import com.api.assistent.assistentkotlin.exception.BusinessException
import com.api.assistent.assistentkotlin.repositorie.ServiceRepositorie
import com.api.assistent.assistentkotlin.service.ServicesService
import com.api.assistent.assistentkotlin.utils.DateOperations
import com.api.assistent.assistentkotlin.utils.QueryOperations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.stereotype.Service

@Service("serviceImpl")
class ServicesServiceImpl : ServicesService {

    @Autowired
    private lateinit var repositorie : ServiceRepositorie

    private val dateUtils = DateOperations

    private val queryOperations = QueryOperations()

   override fun insert (service : ServiceEntitie) : ServiceEntitie? {
       return try {
           service.apply {
               beginDate = dateUtils.convertLocalDateToDate()
               experateDate =  dateUtils.plusDate(6)
           }
           repositorie.save(service)
       } catch (e : Exception) {
           throw BusinessException(INTERNAL_SERVER_ERROR.value(), e.message.toString())
       }

   }

    override fun insertGroup(services : List<ServiceEntitie>): List<ServiceEntitie> {
        return try {
            repositorie.saveAll(services)
        } catch (e : Exception) {
            throw BusinessException(INTERNAL_SERVER_ERROR.value(), e.message.toString())
        }
    }

    override fun findById(id: Long): ServiceEntitie? {
        return try {
            repositorie.getOne(id)
        } catch (e : Exception) {
            throw  BusinessException(INTERNAL_SERVER_ERROR.value(), e.message.toString())
        }
    }

    override fun findAll(): List<ServiceEntitie> {
        return try {
            repositorie.findAll()
        } catch (e : Exception) {
            throw BusinessException(INTERNAL_SERVER_ERROR.value(), e.message.toString())
        }
    }

    override fun findByName(name: String): List<ServiceEntitie> {
        return try {
            repositorie.findByName(name)
        } catch (e : Exception) {
            throw BusinessException(INTERNAL_SERVER_ERROR.value(), e.message.toString())
        }
    }
}