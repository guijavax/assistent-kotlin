package com.api.assistent.assistentkotlin.utils

import org.springframework.http.ResponseEntity

class Util {
    fun returnStatus(obj : Any?) : ResponseEntity<Any?>? {
        return obj?.let {
            if (it is List<*>) {
                if(it.isEmpty()) ResponseEntity.noContent().build() else ResponseEntity.ok(it)
            }
            if (it is String) {
                if("" == it) ResponseEntity.noContent().build() else ResponseEntity.ok(it)
            }
            ResponseEntity.ok(it)
        }
            ?: ResponseEntity.noContent().build()
    }
}