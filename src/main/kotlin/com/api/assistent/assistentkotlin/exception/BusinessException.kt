package com.api.assistent.assistentkotlin.exception

class BusinessException(message: String) : Exception(message) {

    var code : Int? = 0
    var errors : List<String>? = emptyList()

    constructor(code: Int, message: String)  :  this(message) {
        this.code = code
    }

    constructor(code : Int, errors : List<String>) : this("") {
        this.code = code
        this.errors = errors
    }

}