package com.api.assistent.assistentkotlin.controller

import com.api.assistent.assistentkotlin.entities.UserEntitie
import com.api.assistent.assistentkotlin.utils.Routes
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = [Routes.ROOT])
class LoginController {

    @PostMapping("/login")
    fun login (@RequestBody user : UserEntitie) : ResponseEntity<UserEntitie> {
        return ResponseEntity.ok(UserEntitie())
    }

}