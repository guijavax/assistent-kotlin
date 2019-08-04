package com.api.assistent.assistentkotlin.entities

import com.sun.istack.NotNull
import javax.persistence.*
import javax.validation.constraints.Max


@Entity
@Table(name="client")
data class ClientEntitie (
        @Id
        @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "sequence_client")
        @SequenceGenerator(sequenceName = "seq_id_client", name="sequence_client", initialValue = 1, allocationSize=1)
        val clientId : Long? = 0,

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

        @Embedded
        val address: AddressClient? = AddressClient()

){
}