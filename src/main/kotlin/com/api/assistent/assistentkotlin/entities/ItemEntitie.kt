package com.api.assistent.assistentkotlin.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import com.sun.istack.NotNull
import javax.persistence.*

@Entity
@Table(name="item")
data class ItemEntitie(

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_item")
        @SequenceGenerator(name="seq_item", sequenceName = "seq_id_item", allocationSize = 1, initialValue = 1)
        val id : Long? = null,

        @OneToOne
        @JoinColumn
        val productEntitie : ProductEntitie? = null,

        @OneToOne
        @JoinColumn
        val serviceEntitie: ServiceEntitie? = null,

        @ManyToOne
        @JoinColumn(name = "id_item")
        @JsonIgnore
        val cart : CartEntitie? = null,

        @Column
        @NotNull
        val amount : Long? = null

)