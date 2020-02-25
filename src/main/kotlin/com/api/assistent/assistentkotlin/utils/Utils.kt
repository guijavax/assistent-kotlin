package com.api.assistent.assistentkotlin.utils

class Utils {

    companion object {
        fun isEmptyString(value : String) : Boolean{
            return (value != null || value != null || value != "null" || value.trim() != "")
        }
    }
}