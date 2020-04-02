package com.api.assistent.assistentkotlin.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name="type_product")
data class TypeProductEntitie(

        @Id
        @NotNull
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val idType : Long? = null,

        @Column
        @NotNull
        val typeName : String? = "",

        @OneToMany(mappedBy = "typeProductEntitie", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @JsonIgnore
        val types : List<ProductEntitie>? = null

)