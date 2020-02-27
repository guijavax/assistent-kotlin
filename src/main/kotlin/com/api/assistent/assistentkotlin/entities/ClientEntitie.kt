package com.api.assistent.assistentkotlin.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import com.sun.istack.NotNull
import javax.persistence.*
import javax.validation.constraints.Max


@Entity
@Table(name="client")
data class ClientEntitie (
        @Id
        @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "sequence_client")
        @SequenceGenerator(sequenceName = "seq_id_client", name="sequence_client", initialValue = 1, allocationSize=1)
        val idClient : Long? = 0,

        @Column(name = "name")
        @NotNull
        val clientName : String? = null,

        @Column(name="cpf")
        @NotNull
        val cpf : Int? = 0,

        @Column(name="age")
        @NotNull
        @Max(value = 3L)
        val age : Int? = 0,

        @Column(name="zip_code")
        val zipCode : Int = 0,

        @Column(name="street")
        @NotNull
        val street : String? = null,

        @Column(name="number")
        @NotNull
        val number : Int? = 0,

        @Column(name="district")
        @NotNull
        val district : String? = null,

        @JoinColumn(name = "id_city")
        @ManyToOne
        val city : CityEntitie? = null,

        @JoinColumn(name = "id_state")
        @ManyToOne
        val state : StateEntitie? = null,

        @Column(name="country")
        @NotNull
        val country : String? = null,

        @Column(name="telephone")
        val telephone: Int? = 0,

        @OneToOne(mappedBy = "client",cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @JsonIgnore
        val user : UserEntitie? = null

)