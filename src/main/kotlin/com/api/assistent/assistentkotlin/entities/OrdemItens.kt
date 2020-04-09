package com.api.assistent.assistentkotlin.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name="type_option_item")
data class OrdemItens(

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ordem_item")
        @SequenceGenerator(name = "seq  _ordem_item", sequenceName = "seq_id_order_item", allocationSize = 1, initialValue = 1)
        val idItemOrder : Long? = null,

        @OneToMany(mappedBy = "ordemItemProduct", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @JsonIgnore
        val products : List<ProductEntitie>? = null,

        @OneToMany(mappedBy = "ordemItemService", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @JsonIgnore
        val services : List<ServiceEntitie>? = null


)