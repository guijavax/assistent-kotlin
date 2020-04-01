package com.api.assistent.assistentkotlin.generics

interface GenericItemTypeService<T> {
    fun insert (t : T) : T
}