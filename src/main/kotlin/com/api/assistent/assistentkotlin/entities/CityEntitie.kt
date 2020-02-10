package com.api.assistent.assistentkotlin.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import com.sun.istack.NotNull
import javax.persistence.*

@Entity
@Table(name = "cidades")
data class CityEntitie (

        @Id
        val id : Long? = 0,

        @Column(name = "nome")
        @NotNull
        val name : String? = "",

        @Column(name="codigo_ibge")
        val codigoIbge : Double? = 0.0,

        @JoinColumn(name="estado_id")
        @ManyToOne
        val state : StateEntitie? = null,

        @Column(name="populacao_2010")
        val populacao2010 : Long? = 0,

        @Column(name="densidade_demo")
        val densidadeDemo : Double? = 0.0,

        @Column(name="gentilico")
        val gentilico : String? = "",

        @Column(name="area")
        val area : Double? = 0.0,

        @OneToMany(mappedBy = "city", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @JsonIgnore
        @NotNull
        val clients: List<ClientEntitie>? = emptyList()

)