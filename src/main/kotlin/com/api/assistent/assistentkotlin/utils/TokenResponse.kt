package com.api.assistent.assistentkotlin.utils

data class TokenResponse (
        var userId: String = "",
        var userName : String = "",
        var token : String = "",
        var isFirstLogin : Boolean = false){}