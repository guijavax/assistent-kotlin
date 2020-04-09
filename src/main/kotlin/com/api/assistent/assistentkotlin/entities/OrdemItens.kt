package com.api.assistent.assistentkotlin.entities

import javax.persistence.*


data class OrdemItens(

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ordem_item")
        @SequenceGenerator(name = "seq_ordem_item", sequenceName = "seq_id_order_item", allocationSize = 1, initialValue = 1)
        val idItemOrder : Long? = null
)