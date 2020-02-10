package com.api.assistent.assistentkotlin.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import com.sun.istack.NotNull
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "estados")
data class StateEntitie (

        @Id
        @NotNull
        val id: Int? = 0,

        @Column(name = "nome")
        @NotNull()
        val name: String? = "",

        @Column(name="sigla", length = 2)
        @NotNull
        val sigla: String? = "",

        @OneToMany(mappedBy = "state", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @JsonIgnore
        @NotNull
        val citys: List<CityEntitie>? = null,

        @OneToMany(mappedBy = "state", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @JsonIgnore
        @NotNull
        val states: List<ClientEntitie>? = emptyList()
)