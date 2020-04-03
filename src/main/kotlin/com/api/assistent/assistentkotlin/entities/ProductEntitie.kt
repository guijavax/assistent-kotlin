package com.api.assistent.assistentkotlin.entities

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name="products")
data class ProductEntitie(

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_product")
        @SequenceGenerator(name = "seq_product", sequenceName = "seq_id_product", initialValue = 1, allocationSize = 1)
        val idProduct : Long? = null,

        @Column
        @NotNull
        val productName : String? = null,

        @JoinColumn(name="id_type_product")
        @ManyToOne
        val typeProductEntitie: TypeProductEntitie? = null,

        @Column
        @NotNull
        val price : Double? = null,

        @Column
        @NotNull
        val description : String? = null,

        @Column
        @NotNull
        val amount : Int? = null
)
