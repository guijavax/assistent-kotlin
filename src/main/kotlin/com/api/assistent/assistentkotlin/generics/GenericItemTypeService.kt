package com.api.assistent.assistentkotlin.generics

/**
 * @author Guilherme Alvees
 */
interface GenericItemTypeService<T> {

    fun insert (t : T) : T?

    fun insertGroup(t : List<T>) : List<T>

    fun findById(id : Long) : T?

    fun findAll() : List<T>

    fun findByName(name : String) : List<T>

    fun findByType(type : Long) : List<T>
}