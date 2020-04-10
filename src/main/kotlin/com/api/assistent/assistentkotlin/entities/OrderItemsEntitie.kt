package com.api.assistent.assistentkotlin.entities

import com.sun.istack.NotNull
import javax.persistence.*

@Entity
@Table(name="order_itens")
data class OrderItemsEntitie (

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ordem_item")
    @SequenceGenerator(name = "seq_ordem_item", sequenceName = "seq_id_order_item", allocationSize = 1, initialValue = 1)
    val idItemOrder : Long? = null,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val orders : List<OrderEntitie>? = emptyList(),

    @ManyToOne
    @JoinColumn(name="id_product")
    val product : ProductEntitie? = null,

    @ManyToOne
    @JoinColumn(name="id_service")
    val service : ServiceEntitie? = null,

    @Column
    @NotNull
    val amountItem : Integer? = null

)