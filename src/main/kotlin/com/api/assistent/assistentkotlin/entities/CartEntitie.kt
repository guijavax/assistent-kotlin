package com.api.assistent.assistentkotlin.entities

import com.sun.istack.NotNull
import javax.persistence.*

@Entity
@Table(name = "shopping_cart")
data class CartEntitie (

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cart")
        @SequenceGenerator(name = "seq_cart", sequenceName = "seq_id_cart", allocationSize = 1, initialValue = 1)
        val idCart : Long? = null,

        @OneToMany(mappedBy = "cart", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        val products : List<ProductEntitie>? = emptyList(),

        @OneToMany(mappedBy = "cart", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        val services : List<ServiceEntitie>? = emptyList(),

        @Column
        @NotNull
        val amount : Int? = null,

        @Column
        @NotNull
        val codCart : Int? = null

)
