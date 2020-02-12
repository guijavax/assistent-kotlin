package com.api.assistent.assistentkotlin.utils

import org.springframework.http.ResponseEntity

class Util {
    fun returnStatus(obj : Any) : ResponseEntity<Any>? {
        obj.let {
            if (it is List<*>) {
                return if(it.isEmpty()) ResponseEntity.noContent().build() else ResponseEntity.ok(it)
            }
            if (it is String) {
                return if("".equals(it))ResponseEntity.noContent().build() else ResponseEntity.ok(it)
            }
            return if(it != null) ResponseEntity.ok(it) else ResponseEntity.noContent().build()
        }
    }
}