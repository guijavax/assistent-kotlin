package com.api.assistent.assistentkotlin.utils

import com.api.assistent.assistentkotlin.exception.BusinessException
import com.api.assistent.assistentkotlin.security.TokenServiceAuthetication.Companion.LOGGER
import org.apache.logging.log4j.kotlin.logger
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.*

class Utils {

    val LOGGER = logger()

    companion object {

         val UTILS = Utils()

        fun isEmptyString(value : String?) : Boolean{
            return (value != null || value != "null" || value != "")
        }

        fun isNotEmptyList(list : Collection<*>) : Boolean {
            return list != null && list.isNotEmpty()
        }

        fun mountRespoonseEntityException(status : HttpStatus, message : String) : ResponseEntity<Any> {
            UTILS.LOGGER.error(message)
            return ResponseEntity.status(status).body(message)
        }

         fun responseEntity(result: Any): ResponseEntity<Any> {
            return if (result != null ) {
                if (result is List<*> && result.isEmpty()) {
                    ResponseEntity.noContent().build()
                } else {
                    ResponseEntity.ok(result)
                }
            } else {
                ResponseEntity.noContent().build()
            }
        }

        fun mountHttpStatus(e: BusinessException): HttpStatus {
            return if (e.code == 204) HttpStatus.NO_CONTENT else HttpStatus.INTERNAL_SERVER_ERROR
        }
    }
}