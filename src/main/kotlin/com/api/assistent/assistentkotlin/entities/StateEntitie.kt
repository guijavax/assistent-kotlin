package com.api.assistent.assistentkotlin.entities

import com.sun.istack.NotNull
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "estados")
data class StateEntitie (

        @Id
        @NotNull
        val idEstado: Int? = 0,

        @Column(name = "nome")
        @NotNull()
        val name: String? = "",

        @Column(name="sigla", length = 2)
        @NotNull
        val sigla: String? = "",

        @OneToMany(mappedBy = "state", cascade = [CascadeType.ALL])
        @NotNull
        val citys: List<CityEntitie>? = null,

        @OneToMany(mappedBy = "state", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        @NotNull
        val states: List<ClientEntitie>? = emptyList()
)