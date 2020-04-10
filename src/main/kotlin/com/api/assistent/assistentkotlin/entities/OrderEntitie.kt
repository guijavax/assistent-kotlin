package com.api.assistent.assistentkotlin.entities

import com.sun.istack.NotNull
import javax.persistence.*

@Entity
@Table(name="order")
data class OrderEntitie(

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_order")
        @SequenceGenerator(name = "seq_order", sequenceName = "seq_id_order", allocationSize = 1, initialValue = 1)
        val idOrder : Long? = null,

        @Column
        @NotNull
        val amountPayable : Double? = null,

        @Column
        @NotNull
        val stateOrder : Char? = null,

        @Column
        val itens : String? = null,

        @ManyToOne
        @JoinColumn
        val order : OrderItemsEntitie? = null


)