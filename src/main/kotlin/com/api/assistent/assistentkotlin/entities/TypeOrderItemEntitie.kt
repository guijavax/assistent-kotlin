package com.api.assistent.assistentkotlin.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name="type_option_item")
data class TypeOrderItemEntitie(

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ordem_item")
        @SequenceGenerator(name = "seq_ordem_item", sequenceName = "seq_id_order_item", allocationSize = 1, initialValue = 1)
        val idItemOrder : Long? = null,

        @OneToMany(mappedBy = "typeOrderItemEntitieProduct", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @JsonIgnore
        val orderItemProduct : List<ProductEntitie>? = emptyList(),

        @OneToMany(mappedBy = "typeOrderItemEntitieService", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @JsonIgnore
        val orderItemService : List<ServiceEntitie>? = emptyList()
)