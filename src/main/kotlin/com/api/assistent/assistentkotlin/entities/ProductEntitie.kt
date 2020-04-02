package com.api.assistent.assistentkotlin.entities

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name="products")
data class ProductEntitie(

        @Id
        @NotNull
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_product")
        @SequenceGenerator(name = "seq_product", sequenceName = "seq_id_product", initialValue = 1, allocationSize = 1)
        val id : Long? = null,

        @Column
        @NotNull
        val productName : String? = null,

        @ManyToOne
        @NotNull
        val typeProductEntitie: TypeProductEntitie? = null

)
