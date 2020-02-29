package com.api.assistent.assistentkotlin.security

data class TokenResponse (
        var userId: String = "",
        var userName : String = "",
        var token : String = "",
        var isFirstLogin : Boolean = false){}