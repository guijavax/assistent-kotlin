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
        @NotNull
        val itens : List<ItemEntitie>? = null,

        @Column
        @NotNull
        val codCart : Int? = null

)
